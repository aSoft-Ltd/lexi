

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "a kotlin multiplatform logging format solution"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() } //
    if (Targeting.WASM) wasmJs { library() }
    if (Targeting.WASM) wasmWasi { library() }
    if (Targeting.OSX) osxTargets()
    if (Targeting.NDK) ndkTargets()
    if (Targeting.LINUX) linuxTargets()
    if (Targeting.MINGW) mingwTargets()

    sourceSets {
        commonMain.dependencies {
            api(projects.lexiApi)
        }

        commonTest.dependencies {
            implementation(libs.kommander.core)
        }

        if (Targeting.JVM) jvmTest.dependencies {
            implementation(kotlin("test-junit5"))
        }
    }
}



