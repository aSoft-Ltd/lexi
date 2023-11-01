import kotlin.test.Test
import lexi.ConsoleAppender
import lexi.JsonLogFormatter
import lexi.JsonLogFormatterOptions
import lexi.LogLevel

class ConsoleAppenderTest {

    @Test
    fun should_print_less_verbose_errors() {
        val logger = ConsoleAppender()
        logger.debug("Debug test")
        logger.trace("Trace test")
        logger.info("Info test")
        logger.warn("Warning test")
        logger.error("Error test")
        logger.fatal("Failure test")
    }

    @Test
    fun should_print_by_levels() {
        val logger = ConsoleAppender(level = LogLevel.ERROR)
        logger.debug("Debug test")
        logger.trace("Trace test")
        logger.info("Info test")
        logger.warn("Warning test")
        logger.error("Error test")
        logger.fatal("Failure test")
    }

    @Test
    fun should_print_by_levels_in_json_format() {
        val options = JsonLogFormatterOptions(source = true)
        val logger = ConsoleAppender(level = LogLevel.DEBUG, formatter = JsonLogFormatter(options))
        logger.debug("Debug test", "user" to "John")
        logger.trace("Trace test")
        logger.info("Info test")
        logger.warn("Warning test")
        logger.error("Error test")
        logger.fatal("Failure test")
    }
}