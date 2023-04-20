plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    if (Targeting.JVM) jvm {
        library()
        withJava()
    }

    if (Targeting.JS) js(IR) {
        library()
    }

    if (Targeting.WASM) wasm {
        library()
    }

    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A multiplatform logging solution"
)