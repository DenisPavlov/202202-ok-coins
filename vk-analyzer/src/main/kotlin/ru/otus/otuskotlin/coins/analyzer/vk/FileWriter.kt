package ru.otus.otuskotlin.coins.analyzer.vk

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths

private val logger = LoggerFactory.getLogger("FileWriterLogger")

fun Map<out Any, Any>.writeCsv(groupId: String, keyName: String, valueName: String = "count") {

    val fileDir = Paths.get("csv-results")
    val filePath = Paths.get("csv-results/$groupId-$keyName.csv").toAbsolutePath()

    if (!Files.exists(fileDir)) {
        Files.createDirectory(fileDir)
    }

    Files.deleteIfExists(filePath)
    Files.createFile(filePath)

    val writer = Files.newBufferedWriter(filePath)
    val printer = CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(keyName, valueName))
    this.forEach {
        printer.printRecord(it.key, it.value)
    }
    logger.info("Write file: $filePath")
    printer.flush()
    printer.close()
}
