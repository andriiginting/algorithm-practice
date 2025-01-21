import java.util.*

class SnakeGame(private val width: Int, private val height: Int, private val food: Array<IntArray>) {

    private var foodsStack = arrayOf<IntArray>()
    private var snakeBody = ArrayDeque<Int>() //initial state of snake
    private val visited = mutableSetOf<Int>()
    private var collectedFood = 0
    private var foodIndex = 0

    init {
        foodsStack = food

        visited.add(0)
        snakeBody.offer(0)
    }

    fun move(direction: String): Int {
        val head = snakeBody.peekFirst()
        val row = head / width
        val col = head % width

        var newRow = row
        var newCol = col

        //'U', 'D', 'L', or 'R'.
        when (direction) {
            "U" -> {
                newRow--
            }

            "D" -> {
                newRow++
            }

            "L" -> {
                newCol--
            }

            "R" -> {
                newCol++
            }
        }

        if (newRow < 0 || newRow >= height || newCol < 0 || newCol >= width) {
            return -1
        }

        //up or down
        if (foodIndex < food.size && newRow == food[foodIndex][0] && newCol == food[foodIndex][1]) {
            collectedFood++
            foodIndex++
        } else {
            // If not eating, move the tail.
            val tail = snakeBody.pollLast()
            visited.remove(tail)
        }

        val newHead = flatten(newRow, newCol)

        if (visited.contains(newHead)) {
            return -1
        }

        snakeBody.offerFirst(newHead)
        visited.add(newHead)

        return collectedFood
    }

    private fun flatten(row: Int, col: Int): Int {
        return col + width + row
    }
}

fun main() {
    val foods = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(0, 1)
    )
    val game = SnakeGame(3, 2, foods)

    // ["R"],["D"],["R"],["U"],["L"],["U"]
    println(game.move("R"))
    println(game.move("D"))
    println(game.move("R"))
    println(game.move("U"))
    println(game.move("L"))
    println(game.move("U"))

    val result = 10 and 8
    println("bitwise $result and is power of two ${isPowerOfTwos(result)}")
}

private fun isPowerOfTwos(number: Int): Boolean {
    return number % 2 == 0 || number % 2 == 1
}