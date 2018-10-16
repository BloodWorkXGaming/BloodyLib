package atm.bloodworkxgaming.bloodyLib

import atm.bloodworkxgaming.bloodyLib.BloodyLib.MOD_ID
import atm.bloodworkxgaming.bloodyLib.BloodyLib.NAME
import atm.bloodworkxgaming.bloodyLib.BloodyLib.VERSION
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager

@Mod(
        modid = MOD_ID,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
        dependencies = "required:forgelin",
        version = VERSION,
        name = NAME
)
object BloodyLib {
    const val MOD_ID = "bloodylib"
    const val NAME = "bloodylib"
    const val VERSION = "@VERSION@"

    val LOGGER = LogManager.getLogger("BloodyLib")
}