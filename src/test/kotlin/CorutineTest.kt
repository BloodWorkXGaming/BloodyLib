import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import java.util.concurrent.atomic.AtomicInteger

var int = AtomicInteger(0)

fun main(args: Array<String>) {
    println("active threads: " + Thread.activeCount())
    test()
    println("Out")
    println("active threads: " + Thread.activeCount())

    Thread.sleep(10000)
    println("active threads: " + Thread.activeCount())
    // delay(5000)
}


fun test() = async {
    println("Heeyyy")
    println("active threads: " + Thread.activeCount())
    for (i in 0..1000) {
        println(i)
        println("active threads: " + Thread.activeCount())
        delay(1)
    }

    println("active threads: " + Thread.activeCount())

    println("After")
    println("active threads: " + Thread.activeCount())
}