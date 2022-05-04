package leetcode

class KthLargest(private val k: Int, private val nums: IntArray) {
    private val list = nums.sorted().toMutableList()
    private val index = nums.size - 1

    fun add(`val`: Int): Int {
        for (i in 0 until list.size) {

        }
        return `val`
    }

}