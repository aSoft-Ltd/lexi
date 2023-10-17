package lexi

class LoggerFactory {
    val appenders = mutableListOf<Appender>()

    fun addAll(list: List<Appender>?) {
        if (list != null) appenders.addAll(list)
    }

    fun add(appender: Appender?) {
        if (appender != null) appenders.add(appender)
    }

    fun build() = Logger(*appenders.toTypedArray())
}