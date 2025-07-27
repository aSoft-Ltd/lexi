import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
}

description = "Log on android tests without worrying about the android sdk"

kotlin {

//    target {
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_11)
//        }
//    }

    sourceSets {
        val test by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
            }
        }
    }
}