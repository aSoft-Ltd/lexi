plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

android {
    configureAndroid("src/androidMain")
    defaultConfig {
        minSdk = 8
    }
}

kotlin {
    if (Targeting.ANDROID) android { library() }
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.WASM) wasm { library() }
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
                implementation(projects.kommanderCore)
            }
        }

        if (Targeting.ANDROID) {
            val androidTest by getting {
                dependencies {
                    implementation(projects.lexiTestAndroid)
                }
            }
        }

        val otherMain by creating {
            dependsOn(commonMain)
        }

//        val otherTest by creating {
//            dependsOn(otherMain)
//            dependsOn(commonTest)
//        }

        if (Targeting.WASM) {
            val wasmMain by getting {
                dependsOn(otherMain)
            }

//            val wasmTest by getting {
//                dependsOn(otherTest)
//            }
        }

        for (target in nativeTargets) {
            val main by target.compilations.getting {
                defaultSourceSet {
                    dependsOn(otherMain)
                }
            }

            val test by target.compilations.getting {
                defaultSourceSet {
//                    dependsOn(main.defaultSourceSet)
//                    dependsOn(otherTest)
                }
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A multiplatform solution to logging on the console"
)