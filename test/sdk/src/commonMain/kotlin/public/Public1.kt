@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package public

import trial.Cat

fun makeCat1(name: String = "James") = Cat(name)

fun makeAnotherCat1(name: String = "John") = Cat(name)