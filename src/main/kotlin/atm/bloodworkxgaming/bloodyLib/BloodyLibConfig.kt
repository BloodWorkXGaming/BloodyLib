package atm.bloodworkxgaming.bloodyLib

import net.minecraftforge.common.config.Config

@Config(modid = BloodyLib.MOD_ID, name = "bloodymods/bloodylib")
object BloodyLibConfig {
    @Config.Comment("How many ticks to wait in between sending the changed NBT to the clients")
    @JvmStatic
    var incrementalNbtUpdateInterval = 5

    @Config.Comment("How many ticks to wait in between sending the full NBT to the clients")
    @JvmStatic
    var fullNbtUpdateInterval = 40
}
