package atm.bloodworkxgaming.bloodyLib.util

import atm.bloodworkxgaming.bloodyLib.registry.AbstractModBlocks
import atm.bloodworkxgaming.bloodyLib.registry.AbstractModItems
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class AbstractCommonHandler(private val modBlocks: AbstractModBlocks? = null, private val modItems: AbstractModItems? = null) {
    @SubscribeEvent
    open fun registerBlocks(event: RegistryEvent.Register<Block>) {
        modBlocks?.registerBlocks(event.registry)
    }

    @SubscribeEvent
    open fun registerItems(event: RegistryEvent.Register<Item>) {
        modItems?.registerItems(event.registry)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    open fun initModels(event: ModelRegistryEvent) {
        modItems?.initModels(event)
        modBlocks?.initModels(event)
    }
}