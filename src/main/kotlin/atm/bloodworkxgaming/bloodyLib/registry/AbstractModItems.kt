package atm.bloodworkxgaming.bloodyLib.registry

import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

abstract class AbstractModItems(private val dataRegistry: AbstractDataRegistry) {
    open fun registerItems(registry: IForgeRegistry<Item>) {
        for (item in dataRegistry.ITEMS) {
            if (item !is IHasSpecialRegistry) {
                registry.register(item)
            }
        }

        for (block in dataRegistry.BLOCKS) {
            if (block !is IHasSpecialRegistry) {
                registry.register(ItemBlock(block).setRegistryName(block.registryName!!))
            }
        }
    }

    @SideOnly(Side.CLIENT)
    open fun initModels(e: ModelRegistryEvent) {
        dataRegistry.ITEMS.forEach {
            if (it is IHasModel)
                it.initModel(e)
        }
    }
}