package leetcode

class LogSystem() {

    private val logs = mutableListOf<Logger>()

    //mapping time with index within timestamp string
    private val timestampMap = mutableMapOf<String, Int>()

    init {
        //"2017:01:01:23:59:59"
        timestampMap.apply {
            put("Year", 4)
            put("Month", 7)
            put("Day", 10)
            put("Hour", 13)
            put("Minute", 16)
            put("Second", 19)
        }
    }

    //timestamp: Year:Month:Day:Hour:Minute:Second
    fun put(id: Int, timestamp: String) {
        logs.add(Logger(id, timestamp))
    }

    fun retrieve(start: String, end: String, granularity: String): List<Int> {
        val result = mutableListOf<Int>()
        val granular = timestampMap[granularity] ?: 0

        val startTime = start.substring(0, granular)
        val endTime = end.substring(0, granular)

        for (log in logs) {
            val timestamp = log.timestamp.substring(0, granular)

            if (startTime.compareTo(timestamp) <= 0 && timestamp.compareTo(endTime)<= 0) {
                result.add(log.id)
            }
        }

        return result
    }
}

data class Logger(
    val id: Int,
    val timestamp: String
)

/**
 * Your LogSystem object will be instantiated and called as such:
 * var obj = LogSystem()
 * obj.put(id,timestamp)
 * var param_2 = obj.retrieve(start,end,granularity)
 */