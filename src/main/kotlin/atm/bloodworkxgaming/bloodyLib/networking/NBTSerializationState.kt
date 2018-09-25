package atm.bloodworkxgaming.bloodyLib.networking

import atm.bloodworkxgaming.bloodyLib.tile.TileEntityTickingBase

data class NBTSerializationState(private val te: TileEntityTickingBase?, private var value: Boolean = true) {
        fun getAndInvert(): Boolean {
            value = !value
            return !value
        }

        fun getAndSetFalse(): Boolean {
            val temp = value
            value = false
            return temp
        }

        fun setTrue() {
            value = true
        }

        fun scheduleUpdate(){
            value = true
            te?.scheduleUpdate()
        }
    }