package ru.otus.kotlin.helloWorld

import kotlin.test.Test
import kotlin.test.assertEquals

class SealedClassesTest {
    @Test
    fun test() {
        val studentStatus: StudentStatus = Active(courseId = "123")
        val status = when(studentStatus) {
            is NotInCurrentFile -> "1"
            is NotEnrolled -> "2"
            is Graduated -> "4"
            is Active -> "3"
        }
        assertEquals("3", status)
    }
}