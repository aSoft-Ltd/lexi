@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package nested.generics

import nested.ocean.MarineOrganism

sealed interface Fisherman<in M : MarineOrganism> {
    fun fish(organism: M)
}