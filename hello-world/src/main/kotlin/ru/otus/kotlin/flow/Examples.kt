package ru.otus.kotlin.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import java.io.FileWriter
import java.time.Instant
import java.time.ZoneId
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.*
import kotlin.random.Random

// генератор нормально распределенных величин с помощью преобразования Бокса-Мюллера
class RandomDistribution : Sequence<Double> by sequence({
    while (true) {
        val u1 = Random.nextDouble()
        val u2 = Random.nextDouble()

        val z1 = sqrt(-2 * ln(u1)) * cos(2 * PI * u2)
        val z2 = sqrt(-2 * ln(u1)) * sin(2 * PI * u2)

        yield(z1)
        yield(z2)
    }
}) {
    fun scale(mean: Double, std: Double) = map { it * std + mean }
}

// данные датчика
data class Sample(
    val serialNumber: String,
    val value: Double,
    val timestamp: Instant = Instant.now(),
)

interface Detector {
    fun samples(): Flow<Sample>
}

class CoroutineDetector(
    private val serialNumber: String,
    private val sampleDistribution: Sequence<Double>,
    private val samplePeriodMills: Long,
) : Detector {
    override fun samples(): Flow<Sample> =
        flow {
            while (true) {
                val samples = sampleDistribution.iterator()
                emit(Sample(serialNumber, samples.next()))
                delay(samplePeriodMills)
            }
        }
}

class BlockingDetector(
    private val serialNumber: String,
    private val sampleDistribution: Sequence<Double>,
    private val samplePeriodMills: Long,
) : Detector {
    override fun samples(): Flow<Sample> =
        flow {
            while (true) {
                val samples = sampleDistribution.iterator()
                emit(Sample(serialNumber, samples.next()))
                // здесь sleep имитирует блокировку потока из-за вызова блокирующего API
                Thread.sleep(samplePeriodMills)
            }
        }.flowOn(Dispatchers.IO) // чтобы не блокировать потоки, в которых выполняются корутины
}

class CallbackDetector(
    private val serialNumber: String,
    private val sampleDistribution: Sequence<Double>,
    private val samplePeriodMills: Long,
) : Detector {
    override fun samples(): Flow<Sample> =
        callbackFlow {
            val samples = sampleDistribution.iterator()
            val timer = Timer()
            // таймер имитирует работу callback API

            timer.schedule(0L, samplePeriodMills) { // эта лямбда вызывается в потоке callback API
                // нужно использовать канал для потоко безопасной пережачи данных
                // из потока callback API в поток, в котором вызываются корутины
                channel.trySendBlocking(Sample(serialNumber, samples.next()))
            }

            // имитируем возникновение ошибки -для демонстрации стратегий обработки ошибок
            timer.schedule(2000L) {
                channel.close(RuntimeException("Unexpected exception"))
            }

            awaitClose {
                // 1. Корутина не завершилась после запуска callback API
                // 2. Осободить используемые ресурсы, связанные с callback API
                timer.cancel()
            }

        }
}

// с помощью этого расширения будем выбирать максимальное значение датчика
fun <T> Flow<T>.rollingMax(comparator: Comparator<T>): Flow<T> = runningReduce { max, current ->
    maxOf(max, current, comparator)
}


@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
suspend fun main() = coroutineScope {
    val distribution = RandomDistribution().scale(30.0, 3.0)

    val detectors = listOf(
        CoroutineDetector("coroutine", distribution, 500L),
        BlockingDetector("blocking", distribution, 1000L),
        CallbackDetector("callback", distribution, 2000L)
    )

    val period = 1000L
    val samples = detectors
        .map { detector ->
            detector.samples()
                .retry() // перезапуск после сбоя на примере детектора callback API
                .transformLatest { // если значения генерируеются реже, чем нам необходимо
                    emit(it)
                    while (true) {
                        delay(period)
                        emit(it.copy(timestamp = Instant.now()))
                    }
                }
                .sample(period)
        }
        .merge() // собираем данные со всех трех датчиков
        .shareIn(
            this,
            started = SharingStarted.Lazily
        ) // нужно, чтобы сделать flow горячим, для решения последней задачи и только для нее

    samples
        .rollingMax(compareBy { it.value })
        .sample(1000L)
        .onEach { println(it) }
        .launchIn(this)


    withContext(Dispatchers.IO) {
        println("Start collecting samples ...")

        @Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")
        withTimeoutOrNull(10_500L) {
            FileWriter("samples.csv").use { writer ->

                @Suppress("BlockingMethodInNonBlockingContext")
                samples.collect {
                    writer.append(it.serialNumber.padStart(9))
                    writer.append(";")
                    writer.append(it.timestamp.atZone(ZoneId.systemDefault()).toLocalTime().toString())
                    writer.append(";")
                    writer.append(it.value.toString())
                    writer.appendLine()
                }
            }
        }
        println("Stop collecting samples ...")

        coroutineContext.cancelChildren()
    }

}