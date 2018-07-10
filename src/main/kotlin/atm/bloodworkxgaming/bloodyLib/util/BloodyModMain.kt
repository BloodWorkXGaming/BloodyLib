package atm.bloodworkxgaming.bloodyLib.util

import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Suppress("unused")
abstract class BloodyModMain(private val handler: AbstractCommonHandler) {
    init {
        MinecraftForge.EVENT_BUS.register(handler)
    }

    @SubscribeEvent
    open fun registerModels(event: ModelRegistryEvent) {
        handler.initModels(event)
    }
}