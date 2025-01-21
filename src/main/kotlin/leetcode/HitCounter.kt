package leetcode

class HitCounter {
    private val counter = mutableMapOf<Int, Int>() // timestamp to count of hit

    fun hit(timestamp: Int) {
        counter[timestamp] = counter.getOrDefault(timestamp, 0) + 1
    }

    fun getHits(timestamp: Int): Int {
        var count = 0

        for ((key, value) in counter) {
            val timeDiff = timestamp - key

            if (timeDiff < 300) {
                count += value
            }
        }

        return count
    }
}