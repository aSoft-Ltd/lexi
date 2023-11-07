@file:JsExport

package possibilities

sealed interface Possibility<out D> {
    val asUnknown get() = this as? Unknown
    val asSome get() = this as? Some
    val asNone get() = this as? None
}

data object Unknown : Possibility<Nothing>

data class Some<out D>(val data: D) : Possibility<D>

data class None(val error: String) : Possibility<Nothing>

