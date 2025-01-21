import java.util.LinkedList

class Vector2D(vec: Array<IntArray>) {
    private val vector = LinkedList<Int>()

    init {
        initializeVector(vec)
        println(vector)
    }

    fun next(): Int {
        return vector.removeFirst()
    }

    fun hasNext(): Boolean {
        return vector.isNotEmpty()
    }

    private fun initializeVector(vec: Array<IntArray>) {

    }
}