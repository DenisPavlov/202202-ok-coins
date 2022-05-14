import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

val ktorVersion: String by project
val logbackVersion: String by project
val kotlinVersion: String by project
val kotlinSerializationVersion: String by project

kotlin {
    jvm {
        withJava()
    }
    js {
        browser {
            binaries.executable()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-server-compression:$ktorVersion")
                implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")

                // при локальном запуске нужен CORS
                // implementation("io.ktor:ktor-server-cors:$ktorVersion")
            }
        }

        val jsMain by getting {
            fun kotlinw(target: String, version: String) = "org.jetbrains.kotlin-wrappers:kotlin-$target:$version"
            dependencies {
                implementation(kotlinw("react", "18.0.0-pre.332-kotlin-1.6.21"))
                implementation(kotlinw("react-dom", "18.0.0-pre.332-kotlin-1.6.21"))
                implementation(kotlinw("react-router-dom", "6.3.0-pre.332-kotlin-1.6.21"))
                implementation(kotlinw("mui", "5.6.2-pre.332-kotlin-1.6.21"))
                implementation(kotlinw("emotion", "11.9.0-pre.332-kotlin-1.6.21"))
                implementation(kotlinw("mui-icons", "5.6.2-pre.332-kotlin-1.6.21"))

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
            }
        }
    }
}

// include JS artifacts in any JAR we generate and package to jar with all dependencies
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    val taskName = if (project.hasProperty("isProduction")
        || project.gradle.startParameter.taskNames.contains("installDist")
    ) {
        "jsBrowserProductionWebpack"
    } else {
        "jsBrowserDevelopmentWebpack"
    }
    val webpackTask = tasks.getByName<KotlinWebpack>(taskName)
    dependsOn(webpackTask) // make sure JS gets compiled first
    from(File(webpackTask.destinationDirectory, webpackTask.outputFileName)) // bring output file along into the JAR
    manifest {
        attributes("Main-Class" to "ok.coins.web.back.ServerKt")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}
