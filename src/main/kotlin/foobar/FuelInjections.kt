package foobar

fun main() {
    println(solution("4"))
}

fun solution(x: String): Int {
    var fuel = x.toInt()
    var counter = 0
    if (x == "0") {
        return 0
    }

    if(fuel % 4 != 0) {
        fuel += 1
        counter++
    }

    while (fuel > 1) {
        fuel /= 2
        counter++
    }

    return counter
}
