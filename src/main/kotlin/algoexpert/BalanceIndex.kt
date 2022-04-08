package algoexpert

fun main() {
    val result = balanceIndex(listOf(0, 9, -8, 2, 7, 1, 11, -2, 1))
    println(result)
}

fun balanceIndex(array: List<Int>): Int {
    var first = 0
    var last = array.size - 1
    val listSize = array.size - 1
    var maxSum = 0

    while(first < last) {
        val left = getSumValue(array, 0, first)
        val right = getSumValue(array, listSize, last)

        if(left > right) {
            last--
        } else if (left < right){
            first++
        } else if (left == right){
            return first
        }
    }

    return -1
}

fun getSumValue(array: List<Int>, start: Int, end: Int): Int {
    var sum = 0

    sum = array.subList(start, end).sum()

    println("total value $sum, $start, $end")
    return sum
}
