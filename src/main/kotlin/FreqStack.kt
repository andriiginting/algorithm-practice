import java.util.*

class FreqStack {
    private val frequencyMap = mutableMapOf<Int, Int>()
    private val stackMap = mutableMapOf<Int, MutableList<Int>>()
    private var maxFreq = 0

    fun push(`val`: Int) {
        frequencyMap[`val`] = frequencyMap.getOrDefault(`val`, 0) + 1
        val currentFreq = frequencyMap.getOrDefault(`val`, 0)

        stackMap.computeIfAbsent(currentFreq) { mutableListOf() }.add(`val`)

        maxFreq = Math.max(maxFreq, currentFreq)
    }

    /**
    - get the value from the stack

    currentFreqNumber = stackMap.get(maxFreq).pop() // 5

    frequencyMap[currentFreqNumber] = frequencyMap.get(currentFreqNumber) - 1

    if stackMap[currentFreqNumber] is empty
    decrease the maxFreq

    return the currentFreqNumber
     */
    fun pop(): Int {
        return 0
    }
}