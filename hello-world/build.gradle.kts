plugins {
    kotlin("jvm")
}

val kotlinCoroutineVersion: String by project

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion")

    testImplementation(kotlin("test"))
}
