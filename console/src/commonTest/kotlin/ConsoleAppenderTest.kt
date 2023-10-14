import kotlin.test.Test
import lexi.ConsoleAppender
import lexi.LogLevel

class ConsoleAppenderTest {

    @Test
    fun should_print_less_verbose_errors() {
        val logger = ConsoleAppender()
        logger.debug("Debug test")
        logger.info("Info test")
        logger.warn("Warning test")
        logger.error("Error test")
        logger.failure("Failure test")
    }

    @Test
    fun should_print_by_levels() {
        val logger = ConsoleAppender(level = LogLevel.ERROR)
        logger.debug("Debug test")
        logger.info("Info test")
        logger.warn("Warning test")
        logger.error("Error test")
        logger.failure("Failure test")
    }
}