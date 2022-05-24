plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
}

group = "ru.otus.otuskotlin.coins"
version = "0.0.1"

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}