package ru.otus.kotlin.helloWorld

// хочу проверить является ли Unit наследником Ant
// Unit является наследником Any
fun sout(block: () -> Any?) {
    val result = block()
    println(result)
}