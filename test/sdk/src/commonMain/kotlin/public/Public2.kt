@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package public

import trial.Cat

fun makeCat2(name: String = "James") = Cat(name)

fun makeAnotherCat2(name: String = "John") = Cat(name)

interface CatMaker {
    fun make(name: String): Cat
}