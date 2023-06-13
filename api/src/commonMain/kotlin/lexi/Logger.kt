package lexi

import kotlin.reflect.KProperty

class Logger(vararg appenders: Appender) : Appender {
    private val appenders = appenders.toList()

    private val extra = mutableMapOf<String, Any?>()

    fun with(vararg data: Pair<String, Any?>): Logger = Logger(*appenders.toTypedArray()).apply {
        extra.putAll(this@Logger.extra)
        extra.putAll(data.toMap())
    }

    fun with(map: Map<String, Any?>): Logger = with(*map.toList().toTypedArray())

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        appenders.forEach { it.append(level, msg, *(extra.toList() + data.toList()).toTypedArray()) }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Logger = with(
        "source" to (thisRef?.let { it::class.simpleName } ?: "Unknown")
    )

    override fun append(vararg o: Any?) = appenders.forEach { it.append(o) }
}