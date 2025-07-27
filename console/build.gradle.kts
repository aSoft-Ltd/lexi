
import java.lang.System

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "A kotlin multiplatform solution to logging on the console"

configureAndroid("src/androidMain") {
    namespace = "tz.co.asoft.lexi.console"
    defaultConfig {
        minSdk = 8
    }
}

kotlin {
    if (Targeting.ANDROID) androidTarget { library() }
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.WASM) wasmJs { library() }
    if (Targeting.WASM) wasmWasi { library() }
    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    val nativeTargets = osxTargets + ndkTargets + linuxTargets + mingwTargets

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.lexiApi)
                api(projects.lexiFormatters)
            }
        }


        val commonTest by getting {
            dependencies {
                implementation(projects.lexiConfiguration)
                implementation(libs.kommander.core)
//                implementation(kotlinx.serialization.toml) // We need wasm support
            }
        }

        if (Targeting.ANDROID) {
            val androidUnitTest by getting {
                dependencies {
                    implementation(projects.lexiTestAndroid)
                    implementation(kotlin("test-junit5"))
                }
            }
        }

        if (Targeting.JVM) jvmTest.dependencies {
            implementation(kotlin("test-junit5"))
        }

        val otherMain by creating {
            dependsOn(commonMain)
        }


        if (Targeting.WASM) {
            val wasmJsMain by getting {
                dependsOn(otherMain)
            }

            val wasmWasiMain by getting {
                dependsOn(otherMain)
            }
        }

        for (target in nativeTargets) {
            val main by target.compilations.getting {
                defaultSourceSet {
                    dependsOn(otherMain)
                }
            }
        }
    }
}



