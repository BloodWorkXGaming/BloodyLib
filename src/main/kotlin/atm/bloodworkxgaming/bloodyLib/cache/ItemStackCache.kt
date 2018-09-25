package atm.bloodworkxgaming.bloodyLib.cache

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

abstract class ItemStackCache<T>(private val loader: (stack: ItemStack) -> T) {
    private val map = LinkedHashMap<Item, LinkedHashMap<ItemStack, T>>(100, 0.75f, true)

    fun get(stack: ItemStack): T? {
        val item = stack.item
        val itemMap = map[item]

        if (itemMap == null || itemMap.isEmpty()) {
            val loaded = loader(stack)
            val innerMap = LinkedHashMap<ItemStack, T>(10, 0.75f, true)

            innerMap[stack] = loaded
            map[item] = innerMap

            return loaded
        } else {

            for ((k, v) in itemMap) {
                if (ItemStack.areItemStacksEqual(k, stack)) return v
            }

            val loaded = loader(stack)

            itemMap[stack] = loaded
            map[item] = itemMap

            return loaded
        }
    }
}