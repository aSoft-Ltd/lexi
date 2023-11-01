package lexi

import lexi.internal.AbstractAppender
import kotlin.reflect.KProperty

class Logger(
    val source: String,
    private val appenders: List<Appender>,
    private val metadata: Map<String, Any?> = mapOf()
) : AbstractAppender(), Appender {

    fun with(vararg data: Pair<String, Any?>): Logger = Logger(
        source, appenders, metadata + data.toMap()
    )

    fun with(map: Map<String, Any?>): Logger = with(*map.toList().toTypedArray())

    override fun append(log: Log) {
        appenders.forEach { it.append(log.copy(source = source, metadata = metadata + log.metadata)) }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Logger {
        if (thisRef == null) return Logger("Unknown", appenders, metadata)
        return Logger(thisRef::class.simpleName ?: "Unknown", appenders, metadata)
    }
}