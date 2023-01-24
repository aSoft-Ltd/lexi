plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    target {
        targetJava("1.8")
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.lexiApi)
                api(kotlinx.datetime)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A jvm solution to logging on to a file"
)