plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
}

description = "A kotlin multiplatform solution to logging on files"

kotlin {
    target { targetJava("1.8") }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.lexiApi)
                api(kotlinx.datetime)
            }
        }
    }
}