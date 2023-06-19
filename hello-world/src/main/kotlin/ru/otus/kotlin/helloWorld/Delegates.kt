package ru.otus.kotlin.helloWorld

interface Base {
    fun print()
}

interface Same {
    fun samePrint()
}

class SameImpl: Same {
    override fun samePrint() = print("Same!")

}

class BaseImpl: Base {
    override fun print() = println(this::class)
}

class Derived(b: Base, s: Same): Base by b, Same by s

fun main() {
    val baseImpl = BaseImpl()
    val same = SameImpl()


    val derived = Derived(baseImpl, same)
    derived.print()
    derived.samePrint()
}