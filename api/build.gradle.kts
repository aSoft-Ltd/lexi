plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "a kotlin multiplatform logging solution"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.WASM) wasm { library() }
    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.core)
            }
        }
    }
}