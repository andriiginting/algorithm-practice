package hackerrank

fun countPairs(arr: Array<Int>): Long {
    var count = 0L

    for (i in arr.indices) {
        for (j in i + 1 until arr.size) {
            val bitwiseAnd = arr[i] and arr[j]
            if (isPowerOfTwo(bitwiseAnd)) {
                count++
            }
        }
    }

    return count
}

private fun isPowerOfTwo(number: Int): Boolean {
    return number > 0 && number and (number - 1) == 0
}