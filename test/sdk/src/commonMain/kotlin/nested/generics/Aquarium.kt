@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package nested.generics

import nested.ocean.Crustacean
import nested.ocean.Fish
import nested.ocean.MarineOrganism

class Aquarium<out F : MarineOrganism, in S : MarineOrganism> : Fisherman<S> {
    override fun fish(organism: S) {
        TODO("Not yet implemented")
    }

    fun get() : F {
        TODO()
    }

    fun addFish(f: Fish) {
        TODO()
    }

    fun addCrustacean(c: Crustacean) {
        TODO()
    }

    fun transform(f: Fish) : Crustacean {
        TODO()
    }
}