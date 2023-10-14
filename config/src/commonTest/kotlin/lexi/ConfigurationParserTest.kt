package lexi

import kommander.expect
import kommander.expectFunction
import kotlin.test.Test
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.Toml

class ConfigurationParserTest {

    @Serializable
    data class TestConfiguration(
        val logging: LoggingConfiguration? = null
    )

    @Test
    fun should_be_able_to_parse_an_empty_configuration() {
        val config = Toml.decodeFromString(TestConfiguration.serializer(), "")
        expect(config.logging).toBe(null)
    }

    @Test
    fun should_parse_configuration() {
        val raw = """
            [logging]
        """.trimIndent()
        val config = Toml.decodeFromString(TestConfiguration.serializer(), raw)
        expect(config.logging?.level).toBe(null)
        expect(config.logging?.appenders).toBeEmpty()
    }

    @Test
    fun should_parse_configuration_with_a_console_appender() {
        val raw = """
            [logging]
            
            [[logging.appenders]]
            type = "console"
            verbose = false
            level = "debug"
        """.trimIndent()
        val config = Toml.decodeFromString(TestConfiguration.serializer(), raw)
        expect(config.logging?.level).toBe(null)
        expect(config.logging?.appenders).toContainElements()
        val appender = config.logging?.appenders?.firstOrNull() as? ConsoleAppenderConfiguration ?: throw RuntimeException("appenders should container elements but did not")
        expect(appender.level).toBe(LevelConfiguration.debug)
        expect(appender.verbose).toBe(false)
    }

    @Test
    fun should_fail_to_parse_unknown_logging_levels() {
        val raw = """
            [logging]
            
            [[logging.appenders]]
            type = "console"
            verbose = false
            level = "DEBUG"
        """.trimIndent()
        expectFunction { Toml.decodeFromString(TestConfiguration.serializer(), raw) }.toFail()
    }

    @Test
    fun should_parse_even_with_a_config_file_with_unknown_values() {
        val raw = """
            [chess]
            board = "tululu"
            
            [logging]
            
            [[logging.appenders]]
            type = "console"
            verbose = false
            level = "DEBUG"
        """.trimIndent()
        expectFunction { Toml.decodeFromString(TestConfiguration.serializer(), raw) }.toFail()
    }

    @Test
    fun should_parse_a_config_with_valid_file_values() {
        val raw = """
            [logging]
            
            [[logging.appenders]]
            type = "file"
            directory = "/logs"
            verbose = false
            level = "debug"
        """.trimIndent()

        val config = Toml.decodeFromString(TestConfiguration.serializer(), raw)
        expect(config.logging?.level).toBe(null)
        expect(config.logging?.appenders).toContainElements()
        val appender = config.logging?.appenders?.firstOrNull() as? FileAppenderConfiguration ?: throw RuntimeException("appenders should container elements but did not")
        expect(appender.level).toBe(LevelConfiguration.debug)
        expect(appender.verbose).toBe(false)
        expect(appender.directory).toBe("/logs")
    }

    @Test
    fun should_parse_a_config_with_valid__console_and_file_values() {
        val raw = """
            [logging]
            
            [[logging.appenders]]
            type = "console"
            verbose = false
            level = "debug"
            
            [[logging.appenders]]
            type = "file"
            directory = "/logs"
            verbose = false
            level = "debug"
        """.trimIndent()

        val logging = Toml.decodeFromString(TestConfiguration.serializer(), raw).logging
        val appenders = logging?.appenders ?: throw RuntimeException("appenders should container elements but did not")

        expect(logging.level).toBe(null)
        expect(appenders).toContainElements()
        val appender1 = appenders[0] as ConsoleAppenderConfiguration
        expect(appender1.level).toBe(LevelConfiguration.debug)
        expect(appender1.verbose).toBe(false)

        val appender2 = appenders[1] as FileAppenderConfiguration
        expect(appender2.level).toBe(LevelConfiguration.debug)
        expect(appender2.verbose).toBe(false)
        expect(appender2.directory).toBe("/logs")
    }
}