package leetcode

fun isValidSudoku(board: Array<CharArray>): Boolean {
    val seen = hashSetOf<String>()
    for (i in 0 until board[0].size) {
        for (j in 0 until board.size) {
            val current = board[i][j]

            if (current != '.') {
                if (
                    isDuplicateInRow(seen, current, i) ||
                    isDuplicateInCol(seen, current, j) ||
                    isDuplicateInSubBox(seen, current, i, j)
                ) {
                    return false
                }
            }
        }
    }

    return true
}

fun isDuplicateInRow(set: HashSet<String>, value: Char, index: Int): Boolean {
    return !set.add("$value found in row $index")
}

fun isDuplicateInCol(set: HashSet<String>, value: Char, index: Int): Boolean {
    return !set.add("$value found in col $index")
}

fun isDuplicateInSubBox(set: HashSet<String>, value: Char, i: Int, j: Int): Boolean {
    return !set.add("$value found in sub-box ${i / 3} <-> ${j / 3}")
}