rootProject.name = "ok-202202-coins"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
    }
}

include("hello-world")
include("coin-dsl")
// include("vk-analyzer") // enable subproject if you want to parse data from VK
