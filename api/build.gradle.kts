plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "a kotlin multiplatform logging solution"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() } // untill https://youtrack.jetbrains.com/issue/KT-80014 gets fixed // untill https://youtrack.jetbrains.com/issue/KT-80014 gets fixed
    if (Targeting.WASM) wasmJs { library() }
    if (Targeting.WASM) wasmWasi { library() }
    if (Targeting.OSX) osxTargets()
    if (Targeting.NDK) ndkTargets()
    if (Targeting.LINUX) linuxTargets()
    if (Targeting.MINGW) mingwTargets()

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.exports)
        }
    }
}