plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform") version "5.3.0"
    // id("io.kotest.multiplatform") version "5.0.2"
}

kotlin {
    jvm {}
    // linuxX64 {}

    sourceSets {
        val kotestVersion: String by project
        val datetimeVersion: String by project

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))

                implementation(project(":api"))
                implementation(project(":common"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation("io.kotest:kotest-framework-engine:$kotestVersion")
                implementation("io.kotest:kotest-assertions-core:$kotestVersion")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5:$kotestVersion")
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}