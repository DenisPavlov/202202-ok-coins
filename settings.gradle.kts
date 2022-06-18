rootProject.name = "202202-ok-coins"

pluginManagement {
    val kotlinVersion: String by settings
    val kotestVersion: String by settings
    val openapiVersion: String by settings
    val shadowVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("io.kotest.multiplatform") version kotestVersion
        id("org.openapi.generator") version openapiVersion
        id("com.github.johnrengelman.shadow") version shadowVersion
    }
}

include("web-ui")
include("api")
include("mappers")
include("common")
