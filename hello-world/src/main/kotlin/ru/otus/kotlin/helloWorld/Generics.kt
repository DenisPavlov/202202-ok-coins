package ru.otus.kotlin.helloWorld

interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<Long>) {
    val objects: Source<Number> = strs // This is OK, since T is an out-parameter
    objects.nextT()
    // ...
}