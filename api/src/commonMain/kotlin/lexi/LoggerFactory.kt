package lexi

import kotlin.reflect.KProperty

class LoggerFactory(private val name: String = "Unknown") {
    private val appenders = mutableListOf<Appender>()

    fun addAll(list: List<Appender>?) {
        if (list != null) appenders.addAll(list)
    }

    fun add(appender: Appender?) {
        if (appender != null) appenders.add(appender)
    }

    @Deprecated(
        message = "use get instead. A Factory should have a get method not a build method. Those are for builders",
        replaceWith = ReplaceWith("get(source)")
    )
    fun build(source: String = name) = Logger(source, appenders)

    fun get(source: String = "Unknown") = Logger(source, appenders)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Logger = if (thisRef != null) {
        get(thisRef::class.simpleName ?: "Unknown")
    } else get()
}