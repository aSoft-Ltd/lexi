package lexi.external

external interface BrowserConsole {
    fun debug(vararg o: Any?)
    fun trace(vararg o: Any?)
    fun info(vararg o: Any?)
    fun warn(vararg o: Any?)
    fun error(vararg o: Any?)
}