package atm.bloodworkxgaming.bloodyLib.registry

import net.minecraft.block.Block
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

abstract class AbstractModBlocks(private val dataRegistry: AbstractDataRegistry) {
    @SubscribeEvent
    open fun registerBlocks(registry: IForgeRegistry<Block>) {
        for (block in dataRegistry.BLOCKS) {
            registry.register(block)
        }
    }

    @SideOnly(Side.CLIENT)
    open fun initModels(e: ModelRegistryEvent) {
        dataRegistry.BLOCKS.forEach {
            if (it is IHasModel) {
                it.initModel(e)
            }
        }
    }
}