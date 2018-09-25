package atm.bloodworkxgaming.bloodyLib.energy

import atm.bloodworkxgaming.bloodyLib.networking.NBTSerializationState
import net.minecraft.nbt.NBTTagInt
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.EnergyStorage

open class EnergyStorageBase(capacity: Int, maxReceive: Int, maxExtract: Int, energy: Int, private val serializationState: NBTSerializationState?) :
        EnergyStorage(capacity, maxReceive, maxExtract, energy),
        INBTSerializable<NBTTagInt> {
    constructor(capacity: Int, serializationState: NBTSerializationState?) : this(capacity, capacity, capacity, 0, serializationState)
    constructor(capacity: Int, maxTransfer: Int, serializationState: NBTSerializationState?) : this(capacity, maxTransfer, maxTransfer, 0, serializationState)
    constructor(capacity: Int, maxReceive: Int, maxExtract: Int, serializationState: NBTSerializationState?) : this(capacity, maxReceive, maxExtract, 0, serializationState)

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        return super.extractEnergy(maxExtract, simulate).apply { if (this > 0) serializationState?.scheduleUpdate() }
    }

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        return super.receiveEnergy(maxReceive, simulate).apply { if (this > 0) serializationState?.scheduleUpdate() }
    }

    fun extractEnergyInternal(maxExtract: Int, simulate: Boolean): Int {
        val energyExtracted = Math.min(energy, maxExtract)

        if (!simulate)
            energy -= energyExtracted


        return energyExtracted
    }

    fun receiveEnergyInternal(maxReceive: Int, simulate: Boolean): Int {
        val energyReceived = Math.min(capacity - energy, maxReceive)

        if (!simulate)
            energy += energyReceived


        return energyReceived
    }

    override fun deserializeNBT(nbt: NBTTagInt?) {
        energy = nbt?.int ?: 0
    }

    override fun serializeNBT() = NBTTagInt(energy)
}