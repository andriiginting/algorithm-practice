package leetcode

import java.util.*

class ProductOfNumbers() {

    private val product = ArrayDeque<Int>()

    fun add(num: Int) {
        product.add(num)
    }

    fun getProduct(k: Int): Int {
        var result = 1
        product.toList()
            .takeLast(k)
            .forEach {
                result *= it
            }

        return result
    }

}