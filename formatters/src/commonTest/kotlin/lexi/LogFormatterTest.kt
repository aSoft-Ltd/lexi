package lexi

import kommander.expect
import kotlin.test.Test

class LogFormatterTest {

    val log = Log(
        level = LogLevel.DEBUG,
        message = "Testing json log formatter",
        status = LogStatus.Progressing,
        source = "Nameless",
        metadata = mapOf()
    )

    val options = JsonLogFormatterOptions(source = true)

    @Test
    fun should_be_able_to_format_logs_in_a_json_way() {
        val formatter = JsonLogFormatter(options)

        expect(formatter.format(log)).toBe("""
            {  
              "level": "DEBUG",
              "message": "Testing json log formatter",
              "source": "Nameless",
              "status": "Progressing"
            }
        """.trimIndent())
    }

    @Test
    fun should_be_able_to_format_logs_in_an_easy_way() {
        val formatter = SimpleLogFormatter(SimpleLogFormatterOptions(source = true))

        expect(formatter.format(log)).toBe("""
            = = = = = = = = = = = = = S T A R T = = = = = = = = = = = = =
            [DEBUG]: Testing json log formatter
            status: Progressing
            source: Nameless
            = = = = = = = = = = = = = = E N D = = = = = = = = = = = = = =
            
        """.trimIndent())
    }
}