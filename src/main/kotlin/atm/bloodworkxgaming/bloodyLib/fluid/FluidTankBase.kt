package atm.bloodworkxgaming.bloodyLib.fluid

import atm.bloodworkxgaming.bloodyLib.networking.NBTSerializationState
import com.sun.org.apache.xpath.internal.operations.Bool
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank

class FluidTankBase(private val nbtState: NBTSerializationState, capacity: Int, fluid: FluidStack? = null, canFill: Boolean = true, canDrain: Boolean = true)
    : FluidTank(fluid, capacity), INBTSerializable<NBTTagCompound> {

    init {
        this.canDrain = canDrain
        this.canFill = canFill
    }

    constructor(nbtState: NBTSerializationState, capacity: Int, fluid: Fluid, amount: Int) : this(nbtState, capacity, FluidStack(fluid, amount))

    override fun deserializeNBT(nbt: NBTTagCompound?) {
        this.readFromNBT(nbt)
    }

    override fun serializeNBT(): NBTTagCompound = writeToNBT(NBTTagCompound())

    override fun onContentsChanged() {
        nbtState.scheduleUpdate()
    }
}
