package ru.otus.kotlin.helloWorld

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

data class Resource(val a: String)

fun resourceDelegate(resource: Resource = Resource("aaaa")): ReadWriteProperty<Any?, Resource> =
    object : ReadWriteProperty<Any?, Resource> {
        var curValue = resource
        override fun getValue(thisRef: Any?, property: KProperty<*>): Resource = curValue
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Resource) {
            curValue = value
        }
    }

val readOnlyResource: Resource by resourceDelegate()  // ReadWriteProperty as val
var readWriteResource: Resource by resourceDelegate()


fun main() {
    println(readOnlyResource.a)
}