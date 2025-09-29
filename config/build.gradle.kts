plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "A kotlin multiplatform solution to logging on the console"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() } // untill https://youtrack.jetbrains.com/issue/KT-80014 gets fixed // untill https://youtrack.jetbrains.com/issue/KT-80014 gets fixed
    if (Targeting.WASM) wasmJs { library() }
    if (Targeting.OSX) macOsTargets() else listOf()
//    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
    if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        commonMain.dependencies {
            api(projects.lexiConsole)
            api(projects.lexiFile)
            api(kotlinx.serialization.toml)
        }

        commonTest.dependencies {
            implementation(libs.kommander.core)
            implementation(squareup.okio.fake)
        }

        if (Targeting.JVM) jvmTest.dependencies {
            implementation(kotlin("test-junit5"))
        }
    }
}