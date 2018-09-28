package atm.bloodworkxgaming.bloodyLib.tile

import atm.bloodworkxgaming.bloodyLib.BloodyLibConfig
import net.minecraft.util.ITickable

abstract class TileEntityTickingBase : TileEntityBase(), ITickable {
    protected var sendUpdateScheduled: Boolean = false
    protected var lastNBTUpdate: Long = 0
    protected var lastFullNBTUpdate: Long = 0
    override fun update() {
        world ?: return
        if (world.isRemote) return

        val time = world.worldTime
        if (sendUpdateScheduled && time - lastNBTUpdate > BloodyLibConfig.incrementalNbtUpdateInterval) {

            sendUpdate(
                    if (time - lastFullNBTUpdate > BloodyLibConfig.fullNbtUpdateInterval) {
                        lastFullNBTUpdate = time
                        true
                    } else {
                        false
                    })

            markDirtyQuick()

            lastNBTUpdate = time
            sendUpdateScheduled = false
        }

        updateTickRemote()
    }

    abstract fun updateTickRemote()

    fun scheduleUpdate() {
        sendUpdateScheduled = true
    }


}