package algoexpert

fun repeatedMatrixValues(matrix: List<List<Int>>): List<Int> {
    val result = mutableListOf<Int>()
    val repeatedMap = mutableMapOf<Int, Int>()

    for (row in 0 until matrix.size) {
        for (col in 0 until matrix[0].size) {
            val repeat = matrix[row][col]
            repeatedMap[repeat] = repeatedMap.getOrDefault(repeat, 0) + 1
        }
    }

    for ((key, value ) in repeatedMap) {
        if (value >= matrix.size) {
            result.add(key)
        }
    }

    repeatedMap.mapValues {
        if (it.value > matrix.size) {
            result.add(it.key)
        }
    }
    return result
}