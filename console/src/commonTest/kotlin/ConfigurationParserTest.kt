import kotlin.test.Test
import kotlinx.serialization.Serializable
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
//    fun should_be_ble_to_configure_the_console_appender() {
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
//            type = "console"
//            level = "INFO"
//            format = "blue"
//            """
//        )
//        config.logging?.register("console") { it.toConsoleAppender() }
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