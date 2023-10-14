package lexi.formatter

import lexi.JsonLogFormatter
import lexi.JsonLogFormatterOptions
import lexi.SimpleLogFormatter
import lexi.SimpleLogFormatterOptions

fun SimpleLogFormatterConfiguration?.toOptions(
    defaultVerbosity: Boolean?,
    defaultSource: Boolean?,
    defaultStatus: Boolean?,
) = SimpleLogFormatterOptions(
    verbose = this?.verbose ?: defaultVerbosity ?: true,
    status = this?.status ?: defaultStatus ?: true,
    source = this?.status ?: defaultSource ?: true
)

fun JsonFormatterConfiguration?.toOptions(
    defaultVerbosity: Boolean?,
    defaultSource: Boolean?,
    defaultStatus: Boolean?,
) = JsonLogFormatterOptions(
    tab = this?.tab ?: "  ",
    verbose = this?.verbose ?: defaultVerbosity ?: true,
    status = this?.status ?: defaultStatus ?: true,
    source = this?.status ?: defaultSource ?: true
)

fun SimpleLogFormatterConfiguration?.toFormatter(
    defaultVerbosity: Boolean?,
    defaultSource: Boolean?,
    defaultStatus: Boolean?,
) = SimpleLogFormatter(toOptions(defaultVerbosity, defaultSource, defaultStatus))

fun JsonFormatterConfiguration?.toFormatter(
    defaultVerbosity: Boolean?,
    defaultSource: Boolean?,
    defaultStatus: Boolean?,
) = JsonLogFormatter(toOptions(defaultVerbosity, defaultSource, defaultStatus))