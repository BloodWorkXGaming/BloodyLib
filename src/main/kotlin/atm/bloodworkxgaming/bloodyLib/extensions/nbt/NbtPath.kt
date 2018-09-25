package atm.bloodworkxgaming.bloodyLib.extensions.nbt

data class NbtPath(val path: List<String>) : Iterable<String> {
    override fun iterator(): Iterator<String> = path.iterator()
    infix fun to(s: String): NbtPath {
        return this.copy(path = listOf(*path.toTypedArray(), s))
    }

    constructor(vararg strings: String) : this(strings.toList())
    constructor() : this(emptyList())
}