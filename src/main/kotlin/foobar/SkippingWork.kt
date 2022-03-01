package foobar

fun main() {
    println(
        solution(
            intArrayOf(13, 5, 2, 6, 5),
            intArrayOf(13, 5, 2, 5)
        )
    )
}

fun solution(x: IntArray, y: IntArray): Int {
    var result = 0
    if (x.size > y.size) {
        for (id in x) {
            if (!y.contains(id)) {
                result = id
            }
        }
    } else {
        for (id in y) {
            if (!x.contains(id)) {
                result = id
            }
        }
    }

    return result
}