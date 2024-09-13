package lexi

fun Map<String, String>.toJsonFormatterOptions() = JsonLogFormatterOptions(
    verbose = getOrElse("verbose") { "true" }.toBoolean(),
    source = getOrElse("source") { "true" }.toBoolean(),
    status = getOrElse("status") { "true" }.toBoolean()
)

fun Map<String, String>.toSimpleFormatterOptions() = SimpleLogFormatterOptions(
    verbose = getOrElse("verbose") { "true" }.toBoolean(),
    source = getOrElse("source") { "true" }.toBoolean(),
    status = getOrElse("status") { "true" }.toBoolean()
)