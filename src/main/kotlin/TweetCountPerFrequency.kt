import java.util.*

class TweetCounts() {

    private val tweetsStorage = mutableMapOf<String, TreeMap<Int, Int>>()

    fun recordTweet(tweetName: String, time: Int) {
        tweetsStorage.getOrPut(tweetName) { TreeMap() }[time] = tweetsStorage[tweetName]?.get(time)?.inc() ?: 1

        tweetsStorage.values.forEach {
            println(it)
        }
    }

    fun getTweetCountsPerFrequency(freq: String, tweetName: String, startTime: Int, endTime: Int): List<Int> {
        if (tweetsStorage.isEmpty()) return emptyList()

        val tweets = tweetsStorage[tweetName]
        if (tweets.isNullOrEmpty()) return emptyList()

        val chunkType = ChunkFrequency.getChunkType(freq)

        val intervalSize = chunkType.timeInSecond

        val result = mutableListOf<Int>()

        var currentTime = startTime

        while (currentTime <= endTime) {
            val key = tweets.floorKey(currentTime)
            val frequency = if (key != null && key <= endTime) {
                tweets[key]!!
            } else {
                0
            }

            result.add(frequency)
            currentTime += intervalSize
        }

        return result
    }
}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * var obj = TweetCounts()
 * obj.recordTweet(tweetName,time)
 * var param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime)
 */

enum class ChunkFrequency(val timeInSecond: Int) {
    MINUTE(60),
    HOUR(3600),
    DAY(86400),
    UNKNOWN(-1);

    companion object {
        fun getChunkType(freq: String): ChunkFrequency {
            return when (freq) {
                "minute" -> MINUTE

                "hour" -> HOUR

                "day" -> DAY

                else -> UNKNOWN
            }
        }
    }
}

fun main() {
    val tweets = TweetCounts()

    tweets.recordTweet("tw", 0)
    tweets.recordTweet("tw", 40)
    tweets.recordTweet("tw", 20)
    tweets.recordTweet("tw", 59)
    tweets.recordTweet("tw", 9)
    println(tweets.getTweetCountsPerFrequency("minute", "tw", 0, 50))
}