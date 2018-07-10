package atm.bloodworkxgaming.bloodyLib.registry

import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.Item
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistryEntry

@Suppress("PropertyName")
abstract class AbstractDataRegistry {
    val ITEMS: MutableList<Item> = mutableListOf()
    val BLOCKS: MutableList<Block> = mutableListOf()
    val RECIPES: MutableList<IRecipe> = mutableListOf()
    val ENCHANTMENTS: MutableList<Enchantment> = mutableListOf()
}

interface IHasModel {
    @SideOnly(Side.CLIENT)
    fun initModel(e: ModelRegistryEvent) {
        when {
            this is Item -> ModelLoader.setCustomModelResourceLocation(this as Item, 0,
                    ModelResourceLocation((this as IForgeRegistryEntry<*>).registryName!!, "inventory"))
            this is Block -> ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this as Block), 0,
                    ModelResourceLocation((this as IForgeRegistryEntry<*>).registryName!!, "inventory"))
            else -> throw IllegalArgumentException("Unable to register model")
        }
    }
}

interface IHasSpecialRegistry