package atm.bloodworkxgaming.bloodyLib.util

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

object RegistryUtils {
    inline fun <reified T : TileEntity> registerTileEntity(location: ResourceLocation) =
            GameRegistry.registerTileEntity(T::class.java, location)

    inline fun <reified T : TileEntity> registerTileEntity(domain: String, path: String) =
            registerTileEntity<T>(ResourceLocation(domain, path))
}