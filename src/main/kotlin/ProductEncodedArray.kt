fun main() {
    val prod = ProductEncodedArray()

    val input1 = arrayOf(
        intArrayOf(1,1),
        intArrayOf(2,1),
        intArrayOf(1,1),
        intArrayOf(2,1),
        intArrayOf(1,1)
    )

    val input2 = arrayOf(
        intArrayOf(1,1),
        intArrayOf(2,1),
        intArrayOf(1,1),
        intArrayOf(2,1),
        intArrayOf(1,1)
    )

    val result = prod.findRLEArray(input1, input2)
    println(result)
}

class ProductEncodedArray {

    /**
     * encoded1 = [[1,1],[2,1],[1,1],[2,1],[1,1]]
     *encoded2 = [[1,1],[2,1],[1,1],[2,1],[1,1]]
     *
     *
     *
     * The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:
     *
     * Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
     * Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
     * Compress prodNums into a run-length encoded array and return it.
     */
    fun findRLEArray(
        encoded1: Array<IntArray>,
        encoded2: Array<IntArray>
    ): List<List<Int>> {

        val expandedEncoded1 = mutableListOf<Int>()
        val expandedEncoded2 = mutableListOf<Int>()
        val prodNums = mutableListOf<Int>()

        encoded1.forEach {
            expandedEncoded1 += expands(it)
            println("expanded1: $expandedEncoded1")
        }

        encoded2.forEach {
            expandedEncoded2 += expands(it)
            println("expanded2: $expandedEncoded2")
        }


        println("expandedEncoded1: $expandedEncoded1")
        println("expandedEncoded2: $expandedEncoded2")
        for (idx in 0 until expandedEncoded1.size) {
            val multiply = expandedEncoded1[idx] * expandedEncoded2[idx]
            prodNums.add(idx, multiply)
        }
        println("prodNums: $prodNums")

        return compress(prodNums)
    }

    private fun expands(encoded: IntArray): List<Int> {
        val expanded = mutableListOf<Int>()
        var counter = 0
        while (counter < encoded[1]) {
            expanded.add(encoded[0])
            counter++
        }

        return expanded
    }

    private fun compress(list: List<Int>): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val group = list.groupingBy { it }
            .eachCount()

        group.forEach { (t, u) ->
            result.add(listOf(t, u))
        }

        return result
    }
}