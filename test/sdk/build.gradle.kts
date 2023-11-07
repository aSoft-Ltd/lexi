//import types.PurifyTypesTask
import java.net.URI
import org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrLink
import types.purifyTypescriptDefinitions

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
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

val compileSync = tasks.withType<KotlinJsIrLink>().matching {
    it.name.contains("prod", ignoreCase = true)
}.first()

purifyTypescriptDefinitions {
    directory = compileSync.destinationDirectory
    dependsOn(compileSync)
}

//afterEvaluate {
//    val assembleJsPackage by tasks.getting{}
//    val purifyTypes by tasks.getting(PurifyTypesTask::class) {
//        inputFile.set(layout.buildDirectory.file("packages/js/picapital-react.d.ts"))
//        import("FC", "PropsWithChildren", "ReactNode") from "react"
//        imports.add("interface Props { }")
//        dependsOn(assembleJsPackage)
//    }
//}