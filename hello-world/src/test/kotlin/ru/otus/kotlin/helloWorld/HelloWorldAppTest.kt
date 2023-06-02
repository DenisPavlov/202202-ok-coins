package ru.otus.kotlin.helloWorld

import kotlin.test.Test
import kotlin.test.assertEquals

class HelloWorldAppTest {

    @Test
    fun `my first test`() {
        val helloWorldApp = HelloWorldApp()
        assertEquals(helloWorldApp.sayHello(), "Hello world")
    }
}
