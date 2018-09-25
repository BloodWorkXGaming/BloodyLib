package atm.bloodworkxgaming.bloodyLib.cache

import net.minecraft.item.ItemStack
import java.util.*

abstract class SortingLinkedList<T>(c: Collection<T>, private inline val loader: (T, ItemStack) -> Boolean) {
    private val list = LinkedList<T>(c)

    fun get(stack: ItemStack): T? {
        val iter = list.iterator()
        var result: T? = null
        while (iter.hasNext()) {
            val next = iter.next()
            if (loader(next, stack)) {
                result = next
                iter.remove()
                break
            }
        }

        result ?: return null

        list.addFirst(result)

        return result
    }
}