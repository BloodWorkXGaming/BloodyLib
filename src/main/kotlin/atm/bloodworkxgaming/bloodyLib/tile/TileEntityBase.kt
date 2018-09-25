package atm.bloodworkxgaming.bloodyLib.tile

import atm.bloodworkxgaming.bloodyLib.networking.PacketHandler
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity


abstract class TileEntityBase : TileEntity() {

    fun markDirtyClient() {
        markDirty()

        if (hasWorld()) {
            val state = getWorld().getBlockState(getPos())
            getWorld().notifyBlockUpdate(getPos(), state, state, 3)
        }
    }

    fun markDirtyQuick() = world?.markChunkDirty(this.pos, this)

    fun markDirtyNBT()  {
        markDirty()

        sendUpdate()
    }

    fun sendUpdate(incremental: Boolean = false){
        PacketHandler.sendNBTUpdate(this, incremental)
    }

    override fun getUpdatePacket(): SPacketUpdateTileEntity? {
        val nbtTag = NBTTagCompound()
        this.writeClientDataToNBT(nbtTag)
        return SPacketUpdateTileEntity(pos, 1, nbtTag)
    }

    override fun onDataPacket(net: NetworkManager?, packet: SPacketUpdateTileEntity?) {
        packet ?: return
        readClientDataFromNBT(packet.nbtCompound)
    }

    override fun getUpdateTag(): NBTTagCompound {
        return super.getUpdateTag().apply { writeClientDataToNBT(this) }
    }

    /**
     * Override to only sync the data the client needs
     */
    open fun writeClientDataToNBT(tagCompound: NBTTagCompound, incremental: Boolean = false): NBTTagCompound = writeToNBT(tagCompound)

    /**
     * Override to only sync the data the client needs
     */
    open fun readClientDataFromNBT(tagCompound: NBTTagCompound) = readFromNBT(tagCompound)
}