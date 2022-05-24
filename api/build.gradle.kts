plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.openapi.generator")
}

val apiVersion = "v1"
val serializationVersion: String by project

kotlin {
    jvm {}
    js {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDirs("$buildDir/generate-resources/main/src/commonMain/kotlin")
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }
}

openApiGenerate {
    val openapiGroup = "${rootProject.group}.api.$apiVersion"

    // https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/README.adoc#configuration
    generatorName.set("kotlin")
    inputSpec.set("$rootDir/specs/coin-$apiVersion.yaml")
    packageName.set(openapiGroup)
    modelPackage.set(openapiGroup)
    library.set("multiplatform")

    globalProperties.apply {
        put("models", "")
        put("modelDocs", "false")
    }

    // https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/kotlin.md
    configOptions.set(
        mapOf(
            "dateLibrary" to "string",
            "enumPropertyNaming" to "UPPERCASE",
            "collectionType" to "list"
        )
    )
}

tasks {
    allMetadataJar {
        dependsOn(openApiGenerate)
    }

    val compileKotlinJvm by getting {
        dependsOn(openApiGenerate)
    }
    val compileTestKotlinJvm by getting {
        dependsOn(openApiGenerate)
    }

    val compileKotlinJs by getting {
        dependsOn(openApiGenerate)
    }
    val compileTestKotlinJs by getting {
        dependsOn(openApiGenerate)
    }
}
