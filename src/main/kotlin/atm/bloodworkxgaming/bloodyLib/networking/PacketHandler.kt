package atm.bloodworkxgaming.bloodyLib.networking

import atm.bloodworkxgaming.bloodyLib.BloodyLib
import atm.bloodworkxgaming.bloodyLib.tile.TileEntityBase
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.relauncher.Side

object PacketHandler {
    val INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BloodyLib.MOD_ID)!!
    private var id = 0

    init {
        registerPacket<MessageNBTUpdate.MessageNBTUpdateHandler, MessageNBTUpdate>()
    }

    inline fun <reified HANDLER : IMessageHandler<MESSAGE, IMessage>, reified MESSAGE : IMessage> registerPacket(side: Side = Side.CLIENT) {
        registerMessage(HANDLER::class.java, MESSAGE::class.java, side)
    }

    fun <REQ : IMessage, REPLY : IMessage> registerMessage(messageHandler: Class<out IMessageHandler<REQ, REPLY>>, requestMessageType: Class<REQ>, side: Side) {
        INSTANCE.registerMessage(messageHandler, requestMessageType, id++, side)
    }

    /**
     * Using coroutines here for a about 4 times speed improvement over not using them.
     * Basically a fire and forget without spawning a ton of threads
     */
    fun sendToAllAround(message: IMessage, te: TileEntity, range: Int = 64) = launch {
        val pos = te.pos
        synchronized(INSTANCE) {
            INSTANCE.sendToAllAround(message, TargetPoint(te.world.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), range.toDouble()))
        }
    }

    fun sendNBTUpdate(te: TileEntityBase, incremental: Boolean = false) {
        sendToAllAround(MessageNBTUpdate(te, incremental), te)
    }
}
