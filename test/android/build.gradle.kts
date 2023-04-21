plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
}

description = "Log on android tests without worrying about the android sdk"

kotlin {
    target { library() }

    sourceSets {
        val test by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
            }
        }
    }
}