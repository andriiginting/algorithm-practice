package algoexpert

import java.util.*

fun main() {
    println(
        underscorifySubstring("testthis is a testtest to see if testestest it works", "test")
    )
}

fun underscorifySubstring(string: String, substring: String): String {
    return string.replace(substring, "_${substring}_")
}

fun permutations(array: List<String>): MutableList<List<String>> {
    val perm = mutableListOf<List<String>>()
    permutationHelper(0, array.toMutableList(), perm)
    return perm
}

private fun permutationHelper(idx: Int, array: MutableList<String>, permutation: MutableList<List<String>>) {
    if(idx == array.size - 1) {
        permutation.add(array.toList())
        return
    }

    for (j in idx until array.size){
        permSwap(array, idx, j)
        permutationHelper(idx+1, array, permutation)
        permSwap(array, idx, j)
    }
}

private fun permSwap(array: MutableList<String>, i: Int, j:Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}

fun balancedBrackets(str: String): Boolean {
    val map = mutableMapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )

    val deque = ArrayDeque<Char>()
    val open = "({["
    val close = ")]}"

    for(bracket in str) {
        if (str.contains(open)) {
            deque.push(bracket)
        } else {
            if (deque.size == 0) {
                return false
            }

            if (deque.peek() == map[bracket]){
                deque.pop()
            } else {
                return false
            }
        }
    }

    return deque.isEmpty()
}


fun generateDocument(characters: String, document: String): Boolean {
    val charMap = mutableMapOf<Char, Int>()

    for(char in characters) {
        if(char !in charMap) {
            charMap[char] = 0
        }

        charMap[char] = charMap.getOrDefault(char, 0) + 1
    }

    for(doc in document) {
        if(doc !in charMap || charMap[doc] == 0) {
            return false
        }

        charMap[doc] = charMap.getOrDefault(doc, 0) - 1
    }

    return true
}

fun runLengthEncoding(string: String): String {
    val encoding = mutableMapOf<Char, Int>()
    val result = StringBuilder()

    for(char in string) {
        encoding[char] = encoding.getOrDefault(char, 0) + 1
    }

    for((key, value) in encoding) {
        if(value in 2..8) {
            result.append("$value$key")
        } else if (value > 9) {
            var remain = value
            while (remain < 10) {
                val total = 9 % remain
                result.append("$total$key")
                remain = 9 % remain
            }
            result.append("$remain$key")
        } else {
            result.append("$key")
        }
    }

    return result.toString()
}