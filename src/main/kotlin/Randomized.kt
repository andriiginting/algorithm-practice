import kotlin.random.Random


class RandomizedSet() {

    private val valueToIdx = mutableMapOf<Int, Int>()
    private val result = mutableListOf<Int>()

    fun insert(`val`: Int): Boolean {
        if (valueToIdx.containsKey(`val`)) return false

        result.add(`val`)
        valueToIdx[`val`] = result.lastIndex

        return true
    }

    fun remove(`val`: Int): Boolean {
        if (!valueToIdx.containsKey(`val`)) return false

        val idx = valueToIdx[`val`]!!
        if (idx < result.lastIndex) {
            val lastVal = result[result.lastIndex]
            result[idx] = lastVal
            valueToIdx[lastVal] = idx
        }
        result.removeAt(result.lastIndex)
        valueToIdx.remove(`val`)
        return true
    }

    fun getRandom(): Int {
        val randomIdx = Random.nextInt(result.size)
        return result[randomIdx]
    }

}