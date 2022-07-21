package leetcode

fun main() {
    val instance = numEquivDominoPairs(
        arrayOf(
            intArrayOf(1,2),
            intArrayOf(1,2),
            intArrayOf(1,1),
            intArrayOf(1,2),
            intArrayOf(2,2)
        )
    )

    println(instance)
}

/*
    [
        [1,2],
        [2,1],
        [3,4],
        [5,6]
    ]
     */
fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
    val pairs = mutableListOf<Pair<Int, Int>>()
    var first = 0
    var second = dominoes.size - 1

    while(first < dominoes.size) {
        while (second <= dominoes.size) {
            if(isEquivalent(dominoes[first], dominoes[second])) {
                pairs.add(Pair(first, second))
            }
            second--
        }
        first++
    }

    return pairs.size
}

//[a, b] -> i
//[c, d] -> j
// if a == c and b == d
// or if a == d and b == c
fun isEquivalent(first: IntArray, second: IntArray): Boolean {
    val firstRotation = (first[0] == second[0] && first[1] == second[1])
    val secondRotation = (first[0] == second[1] && first[1] == second[0])

    return firstRotation || secondRotation
}

class MovingAverage(size: Int) {
    val list = mutableListOf<Int>()
    val listSize = size
    fun next(`val`: Int): Double {
        list.add(`val`)
        val average = list.takeLast(listSize)
        return average.average()
    }
}
