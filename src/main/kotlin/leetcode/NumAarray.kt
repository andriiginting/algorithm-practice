package leetcode

class NumArray(private val nums: IntArray) {
    fun sumRange(left: Int, right: Int): Int {
        var result = 0
        var leftIdx = left

        while(leftIdx <= right) {
            val sum = nums[leftIdx]
            result += sum
            leftIdx++
        }
        return result
    }

    fun update(index: Int, `val`: Int) {
        //need tp update index into val

        nums[index] = `val`
    }

}