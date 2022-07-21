
class MedianFinder() {

    /** initialize your data structure here. */
    private val list = mutableListOf<Int>()

    fun addNum(num: Int) {
        list.add(num)
    }

    fun findMedian(): Double {

        val size = list.size
        return if (list.size % 2 != 0) {
            list[size / 2].toDouble()
        } else {
            (list[(size - 1) / 2] + list[size / 2]) / 2.0
        }
    }

}

fun main() {
    println(0/2)
}