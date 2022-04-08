package algoexpert

fun main() {

}

fun buildFailures(buildRuns: List<List<Boolean>>): Int {
    var count = -1
    var max = 0
    for(ci in buildRuns) {
        if (max < calculateSuccessPercentage(ci)) {
            count++
            max = Math.max(calculateSuccessPercentage(ci), max)
        }
    }

    return count
}

fun calculateSuccessPercentage(list: List<Boolean>): Int {
    return  list.count { it.not() }
}