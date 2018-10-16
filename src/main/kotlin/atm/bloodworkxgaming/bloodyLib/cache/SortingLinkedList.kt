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
class SortingLinkedList<K, T>(
        private inline val loader: (iterator: K, listElement: T) -> Boolean,
        private val nonMoveCount: Int = 3, c: Collection<T> = emptyList()
) : MutableList<T> {
    private val list = LinkedList<T>(c)

    operator fun get(stack: K): T? {
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

    override fun iterator(): MutableIterator<T> = list.iterator()
    override val size: Int
        get() = list.size

    override fun contains(element: T): Boolean = list.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = list.containsAll(elements)
    override operator fun get(index: Int): T = list[index]
    override fun indexOf(element: T): Int = list.indexOf(element)
    override fun isEmpty(): Boolean = list.isEmpty()
    override fun lastIndexOf(element: T): Int = list.lastIndexOf(element)
    override fun listIterator(): MutableListIterator<T> = list.listIterator()
    override fun listIterator(index: Int): MutableListIterator<T> = list.listIterator(0)
    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> = list.subList(fromIndex, toIndex)
    override fun add(index: Int, element: T) = list.add(index, element)
    override fun addAll(index: Int, elements: Collection<T>): Boolean = list.addAll(index, elements)
    override fun addAll(elements: Collection<T>): Boolean = list.addAll(elements)
    override fun remove(element: T): Boolean = list.remove(element)
    override fun removeAll(elements: Collection<T>): Boolean = list.removeAll(elements)
    override fun removeAt(index: Int): T = list.removeAt(index)
    override fun retainAll(elements: Collection<T>): Boolean = list.retainAll(elements)
    override fun set(index: Int, element: T): T = list.set(index, element)
    override fun add(element: T): Boolean = list.add(element)
    override fun clear() = list.clear()


}