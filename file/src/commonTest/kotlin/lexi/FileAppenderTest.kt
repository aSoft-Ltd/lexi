package lexi

import kommander.expect
import kotlin.test.Test
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem

class FileAppenderTest {

    object FrozenClock : Clock {
        override fun now() = Instant.fromEpochMilliseconds(0)
    }

    val directory = "/logs".toPath()
    val system = FakeFileSystem()
    val appender by lazy {
        val options = FileAppenderOptions(
            system = system,
            directory = directory,
            clock = FrozenClock,
            formatter = SimpleLogFormatter()
        )
        FileAppender(options)
    }

    @Test
    fun should_append_to_a_test_file() {
        appender.info("test")

        val file = system.flattenedFiles(directory).first()
        val text = system.read(file) { readUtf8() }
        expect(file).toBe(directory / "1970" / "01" / "01" / "00.log")
        expect(text).toBe("""
            = = = = = = = = = = = = = S T A R T = = = = = = = = = = = = =
            [INFO]: test
            Source: Unknown
            = = = = = = = = = = = = = = E N D = = = = = = = = = = = = = =
            
        """.trimIndent())
    }

    fun FileSystem.flattenedFiles(path: Path): List<Path> {
        val fop = metadata(path)
        return if (fop.isRegularFile) {
            listOf(path)
        } else {
            list(path).flatMap { flattenedFiles(it) }
        }
    }
}