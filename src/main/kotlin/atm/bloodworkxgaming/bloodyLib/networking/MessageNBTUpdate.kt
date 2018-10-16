package atm.bloodworkxgaming.bloodyLib.networking

import atm.bloodworkxgaming.bloodyLib.tile.TileEntityBase
import io.netty.buffer.ByteBuf
import net.minecraft.client.Minecraft
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MessageNBTUpdate : IMessage {

    private var x: Int = 0
    private var y: Int = 0
    private var z: Int = 0
    private var tag: NBTTagCompound? = null

    constructor() {}

    constructor(te: TileEntityBase, incremental: Boolean = false) {
        this.x = te.pos.x
        this.y = te.pos.y
        this.z = te.pos.z
        this.tag = te.writeClientDataToNBT(NBTTagCompound(), incremental)
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(x)
        buf.writeInt(y)
        buf.writeInt(z)
        ByteBufUtils.writeTag(buf, tag)
    }

    override fun fromBytes(buf: ByteBuf) {
        this.x = buf.readInt()
        this.y = buf.readInt()
        this.z = buf.readInt()
        this.tag = ByteBufUtils.readTag(buf)
    }

    class MessageNBTUpdateHandler : IMessageHandler<MessageNBTUpdate, IMessage> {
        @SideOnly(Side.CLIENT)
        override fun onMessage(msg: MessageNBTUpdate, ctx: MessageContext): IMessage? {
            Minecraft.getMinecraft().addScheduledTask {
                println("Message 1")
                val entity = Minecraft.getMinecraft().player.entityWorld.getTileEntity(BlockPos(msg.x, msg.y, msg.z)) as? TileEntityBase ?: return@addScheduledTask
                println("Message 2")
                val tag = msg.tag ?: return@addScheduledTask
                println("Message 3")
                entity.readClientDataFromNBT(tag)
                println("Message 3")
            }
            return null
        }
    }

}
