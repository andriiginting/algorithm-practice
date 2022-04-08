package leetcode

import java.util.*

fun main() {

}

class PhoneDirectory(private  val maxNumbers: Int) {
    private val directory = mutableMapOf<Int, Boolean>()
    init {
        for (i in 0 until maxNumbers) {
            directory[i] = directory.getOrDefault(i, false)
        }
    }

    fun get(): Int {
        var number = 0
        directory.toSortedMap().forEach { key, value ->
            if (!value) {
                number = key
                directory[key] = true
            }
        }

        return number
    }

    fun check(number: Int): Boolean {
        for ((key, value ) in directory) {
            if (number == key) {
                return value
            }
        }

        return false
    }

    fun release(number: Int) {
        directory[number] = directory.getOrDefault(number, false)
    }

}