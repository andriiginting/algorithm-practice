package algoexpert

import java.util.*

fun main() {
    println(
        runLengthEncoding("AAAAAAAAAAAAABBCCCCDD")
    )
}

fun tournamentWinner(competitions: List<List<String>>, results: List<Int>): String {
    val rank = mutableMapOf<String, Int>()

    for(i in 0 until competitions.size) {
        val homeAway = results[i]
        val winner = getWinner(competitions[i], homeAway)
        rank[winner] = rank.getOrDefault(winner, 0) + 1
    }

    val result = rank.maxBy { it.value }
    return result?.key.orEmpty()
}

private fun getWinner(list: List<String>, winner: Int): String {
    return if (winner == 0) {
        list[1]
    } else {
        list[0]
    }
}

fun shortenPath(path: String): String {
    /*
    /foo/bar/baz
     */
    val split = path.replace("//","/")
        .split("/")

    println(split)
    val builder = StringBuilder()

    var idx = split.size - 1
    while (idx != 0) {
        val dir = split[idx]
        if (dir == "..") {
            idx -= 2
        } else if(dir != ".") {
            builder.append("$dir/")
            idx--
        }
    }

    return builder.toString()
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
    var counter = 0
    var builder = StringBuilder()

    if (string.length < 2) {
        return string
    }

    for(i in 1 until string.length) {
        if (builder[i] == builder[i-1]) {
            counter++
            builder.append("$counter${string[i-1]}")
        } else {
            counter = 0
            builder.append("${string[i-1]}")
        }
    }

    return string
}