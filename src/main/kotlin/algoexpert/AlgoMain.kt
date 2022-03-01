package algoexpert

import java.util.*

fun main() {
    println(

    )
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