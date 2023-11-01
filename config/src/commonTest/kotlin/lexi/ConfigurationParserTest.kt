package lexi

import kommander.expect
import kommander.expectFunction
import kotlin.test.Test
import kotlinx.serialization.Serializable
import lexi.appender.ConsoleAppenderConfiguration
import lexi.appender.FileAppenderConfiguration
import net.peanuuutz.tomlkt.Toml

class ConfigurationParserTest {

    private val codec = Toml { 
        ignoreUnknownKeys = true
    }
    @Serializable
    data class TestConfiguration(
        val logging: LoggingConfiguration? = null
    )

    @Test
    fun should_be_able_to_parse_an_empty_configuration() {
        val config = codec.decodeFromString(TestConfiguration.serializer(), "")
        expect(config.logging).toBe(null)
    }

    @Test
    fun should_parse_configuration() {
        val raw = """
            [logging]
        """.trimIndent()
        val config = codec.decodeFromString(TestConfiguration.serializer(), raw)
        expect(config.logging?.level).toBe(null)
        expect(config.logging?.appenders).toBeEmpty()
    }

    @Test
    fun should_parse_configuration_with_a_console_appender() {
        val raw = """
            [logging]
            
            [[logging.appenders]]
            type = "console"
            level = "debug"
            format.verbose = false
            format.type = "json"
        """.trimIndent()
        val config = codec.decodeFromString(TestConfiguration.serializer(), raw)
        expect(config.logging?.level).toBe(null)
        expect(config.logging?.appenders).toContainElements()
        val appender = config.logging?.appenders?.firstOrNull() as? ConsoleAppenderConfiguration ?: throw RuntimeException("appenders should container elements but did not")
        expect(LogLevel.parse(appender.level)).toBe(LogLevel.DEBUG)
        expect(appender.format?.verbose).toBe(false)
    }

    @Test
    fun should_parse_even_with_a_config_file_with_unknown_values() {
        val raw = """
            [chess]
            board = "tululu"
            
            [logging]
            
            [[logging.appenders]]
            type = "consolidation"
            verbose = false
            level = "DEBUG"
        """.trimIndent()
        expectFunction { codec.decodeFromString(TestConfiguration.serializer(), raw) }.toFail()
    }

    @Test
    fun should_parse_a_config_with_valid_file_values() {
        val raw = """
            [logging]
            
            [[logging.appenders]]
            type = "file"
            directory = "/logs"
            level = "debug"
            format.type = "json"
            format.verbose = false
        """.trimIndent()

        val config = codec.decodeFromString(TestConfiguration.serializer(), raw)
        expect(config.logging?.level).toBe(null)
        expect(config.logging?.appenders).toContainElements()
        val appender = config.logging?.appenders?.firstOrNull() as? FileAppenderConfiguration ?: throw RuntimeException("appenders should container elements but did not")
        expect(LogLevel.parse(appender.level)).toBe(LogLevel.DEBUG)
        expect(appender.format?.verbose).toBe(false)
        expect(appender.directory).toBe("/logs")
    }

    @Test
    fun should_parse_a_config_with_valid__console_and_file_values() {
        val raw = """
            [logging]
            
            [[logging.appenders]]
            type = "console"
            level = "debug"
            format.type = "json"
            format.verbose = false
            
            [[logging.appenders]]
            type = "file"
            level = "debug"
            directory = "/logs"
            format.type = "simple"
            format.verbose = false
        """.trimIndent()

        val logging = codec.decodeFromString(TestConfiguration.serializer(), raw).logging
        val appenders = logging?.appenders ?: throw RuntimeException("appenders should container elements but did not")

        expect(logging.level).toBe(null)
        expect(appenders).toContainElements()
        val appender1 = appenders[0] as ConsoleAppenderConfiguration
        expect(LogLevel.parse(appender1.level)).toBe(LogLevel.DEBUG)
        expect(appender1.format?.verbose).toBe(false)

        val appender2 = appenders[1] as FileAppenderConfiguration
        expect(LogLevel.parse(appender2.level)).toBe(LogLevel.DEBUG)
        expect(appender2.format?.verbose).toBe(false)
        expect(appender2.directory).toBe("/logs")
    }
}