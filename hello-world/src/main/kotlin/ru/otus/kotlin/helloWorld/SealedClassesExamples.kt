package ru.otus.kotlin.helloWorld

sealed class StudentStatus

object NotEnrolled: StudentStatus()

data class Active(val courseId: String): StudentStatus()

object Graduated: StudentStatus()