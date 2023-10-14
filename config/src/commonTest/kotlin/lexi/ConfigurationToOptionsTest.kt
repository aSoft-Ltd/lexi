package lexi

import kommander.expect
import kotlin.test.Test
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.Toml
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem

class ConfigurationToOptionsTest {
    @Serializable
    data class TestConfiguration(
        val logging: LoggingConfiguration? = null
    )

    @Test
    fun should_use_default_values_if_they_are_not_specified_in_the_console_configuration() {
        val raw = """
            [logging]
            level = "info"
            verbose = true
            
            [[logging.appenders]]
            type = "console"
        """.trimIndent()
        val options = Toml.decodeFromString(TestConfiguration.serializer(), raw).logging?.toOptions(
            system = FakeFileSystem(),
            clock = Clock.System,
            prefix = "/app/test".toPath()
        )?.firstOrNull() as ConsoleAppenderOptions
        expect(options.level).toBe(LogLevel.INFO)
        expect(options.verbose).toBe(true)
    }

    @Test
    fun should_use_default_values_if_they_are_not_specified_in_the_file_configuration() {
        val raw = """
            [logging]
            level = "info"
            verbose = true
            
            [[logging.appenders]]
            type = "file"
            directory = "/logs"
        """.trimIndent()
        val options = Toml.decodeFromString(TestConfiguration.serializer(), raw).logging?.toOptions(
            system = FakeFileSystem(),
            clock = Clock.System,
            prefix = "/app/test".toPath()
        )?.firstOrNull() as FileAppenderOptions
        expect(options.level).toBe(LogLevel.INFO)
        expect(options.directory).toBe("/logs".toPath())
    }
}