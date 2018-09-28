package atm.bloodworkxgaming.bloodyLib.cache

import java.util.*

/**
 * This is a delegation of a linked list:
 * It is only efficient in the following cases: (or atleast more efficient than a set or arraylist)
 *      There are no hashcode or eqauls method
 *      The loader function is not expensive to compute and therefore doesn't make much sense to cache
 *
 * @param nonMoveCount How many elements from the front the element can be before starting it should start to move it to the front
 */
class SortingLinkedList<K, T>(private inline val loader: (iterator: K, listElement: T) -> Boolean, private val nonMoveCount: Int = 3, c: Collection<T> = emptyList()) : Iterable<T>{

    private val list = LinkedList<T>(c)

    fun get(stack: K): T? {
        val iter = list.iterator()
        var result: T? = null
        var counter = 0
        while (iter.hasNext()) {
            val next = iter.next()
            counter++

            if (loader(stack, next)) {
                result = next

                if (counter > nonMoveCount)
                    iter.remove()
                break
            }

        }

        result ?: return null

        if (counter > nonMoveCount)
            list.addFirst(result)

        return result
    }

    override fun iterator(): Iterator<T> = list.iterator()
}