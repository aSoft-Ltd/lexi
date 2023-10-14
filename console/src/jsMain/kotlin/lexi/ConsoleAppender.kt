package lexi

import kotlin.js.Console
import kotlin.js.console as consl

actual class ConsoleAppender actual constructor(
    var options: ConsoleAppenderOptions
) : Appender, Console {

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level >= options.level) {
            val printer: (Array<out Any?>) -> Unit = when (level) {
                LogLevel.INFO -> { ar -> consl.info(*ar) }
                LogLevel.DEBUG -> { ar -> consl.log(*ar) }
                LogLevel.WARNING -> { ar -> consl.warn(*ar) }
                LogLevel.ERROR -> { ar -> consl.error(*ar) }
                LogLevel.FAILURE -> { ar -> consl.error(*ar) }
            }

            val log = Log(level, msg, null, data.toMap())
            val formatted = options.formatter.format(log)
            if (options.formatter is JsonLogFormatter) {
                printer(arrayOf(JSON.parse<dynamic>(formatted)))
            } else {
                printer(arrayOf(formatted))
            }
        }
    }

    override fun append(vararg o: Any?) = consl.log(*o)

    fun table(obj: Any) = consl.asDynamic().table(obj)

    override fun dir(o: Any) = consl.dir(o)

    override fun error(vararg o: Any?) = consl.error(*o)

    override fun info(vararg o: Any?) = consl.info(*o)

    override fun log(vararg o: Any?) = consl.log(*o)

    override fun warn(vararg o: Any?) = consl.warn(*o)
}