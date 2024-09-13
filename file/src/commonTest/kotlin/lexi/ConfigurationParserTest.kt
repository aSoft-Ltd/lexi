package lexi

import kotlin.test.Test
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import okio.FileSystem
import okio.fakefilesystem.FakeFileSystem
//import net.peanuuutz.tomlkt.Toml
//
//class ConfigurationParserTest {
//
//    private val codec = Toml {
//        ignoreUnknownKeys = true
//    }
//
//    @Serializable
//    data class TestConfiguration(
//        val logging: LoggingConfiguration? = null
//    )
//
//    @Test
//    fun should_be_able_to_detect_the_configured_appenders() {
//        val config = codec.decodeFromString(
//            TestConfiguration.serializer(),
//            """
//            [logging]
//            level = "DEBUG"
//            source = true
//            status = false
//            verbose = true
//
//            [[logging.appender]]
//            type = "file"
//            level = "DEBUG"
//            format = "json"
//            directory = "logs"
//            """
//        )
//        val system = FakeFileSystem()
//        val clock = Clock.System
//        config.logging?.register("file") { it.toFileAppender(system, clock) }
//        val factory = config.logging.build()
//        val logger by factory
//        logger.info("Test log")
//    }
//
//    @Test
//    fun should_be_able_to_create_a_static_appender() {
//        val config = codec.decodeFromString(
//            TestConfiguration.serializer(),
//            """
//            [logging]
//            level = "DEBUG"
//            source = true
//            status = false
//            verbose = true
//            """
//        )
//
//        config.logging?.register { ConsoleAppender() }
//
//        val factory = config.logging.build()
//        val logger by factory
//        logger.info("Test log")
//    }
//}