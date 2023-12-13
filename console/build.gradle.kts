import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import java.lang.System

plugins {
    id("com.android.library")
    kotlin("multiplatform")
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
    if (Targeting.ANDROID) android { library() }
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
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.core)
            }
        }

        if (Targeting.ANDROID) {
            val androidUnitTest by getting {
                dependencies {
                    implementation(projects.lexiTestAndroid)
                }
            }
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

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = npm.versions.node.version.get()
    nodeDownloadBaseUrl = npm.versions.node.url.get()
}

rootProject.tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}

tasks.named("wasmJsTestTestDevelopmentExecutableCompileSync").configure {
    mustRunAfter(tasks.named("jsBrowserTest"))
    mustRunAfter(tasks.named("jsNodeTest"))
}