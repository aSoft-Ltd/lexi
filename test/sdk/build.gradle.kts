import dev.petuska.npm.publish.task.NpmPublishTask
import types.PurifyTypesTask
import java.net.URI

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("dev.petuska.npm.publish")
}

apply<types.PostProcessTypescriptTypesPlugin>()

kotlin {
    js(IR) {
        val main by compilations
        main.outputModuleName = "lexi-react"
        browserLib {
            commonWebpackConfig {
                sourceMaps = false
            }
        }
//        useEsModules()
        generateTypeScriptDefinitions()
        binaries.library()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(projects.lexiConsole)
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(libs.kommander.coroutines)
            }
        }
    }
}

npmPublish {
    organization.set("picortex")
    registries {
        register("github") {
            uri.set("https://npm.pkg.github.com/")
            authToken.set(providers.environmentVariable("GH_TOKEN"))
        }

        register("andylamax") {
            uri.set("http://localhost:1040")
            authToken.set("andylamax")
        }
    }

    packages {
        val js by getting {
            version.set("${project.version}")
            packageName.set("picapital-react")
            readme.set(file("README.md"))
            packageJson {
                types.set("index.d.ts")

                repository {
                    type.set("git")
                    url.set("https://github.com/picortex/picapital.git")
                }

                devDependencies {
                    set("@types/react", kotlinw.versions.react.types.get())
                }
            }
        }
    }
}

afterEvaluate {
    val assembleJsPackage by tasks.getting{}
    val purifyTypes by tasks.getting(PurifyTypesTask::class) {
        inputFile.set(layout.buildDirectory.file("packages/js/picapital-react.d.ts"))
        import("FC", "PropsWithChildren", "ReactNode") from "react"
        imports.add("interface Props { }")
        dependsOn(assembleJsPackage)
    }

    tasks.withType(NpmPublishTask::class.java).configureEach {
        dependsOn(purifyTypes)
    }
}