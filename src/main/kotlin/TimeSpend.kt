
class TimeSpend {

    /**
     * static String [][] requests = {
     *     {"create", "xyz", "1", "1619916081"}, // Sat May 01 2021 17:41:21 GMT-0700
     *     {"join", "xyz", "2", "1619916681"}, // Sat May 01 2021 17:51:21 GMT-0700
     *     {"create", "abc", "3", "1619916881"}, //12:01
     *     {"leave", "xyz", "2", "1619920281"},
     *     {"join", "abc", "4", "1619920881"},
     *     {"create", "ghi", "5", "1619923999"},
     *     {"leave", "xyz", "1", "1619923881"},
     *     {"leave", "abc", "3", "1619927481"}, //12:07 time spent = 6 minutes
     *     {"leave", "abc", "4", "1619927481"},
     *     {"leave", "ghi", "5", "1619958001"}
     * };
     */

    val spaces = mutableMapOf<String, MutableList<UsersTime>>()

    fun create(spaceId: String, id: String) {
        val availableSpace = spaces.getOrDefault(spaceId, mutableListOf())

        val isNotExist = availableSpace.none { user ->
            user.userId == id
        }

        if (isNotExist) {
            availableSpace.add(UsersTime(id, System.currentTimeMillis()))
        }
        println("created $spaces")
    }

    fun join(spaceId: String, id: String) {
        val availableSpace = spaces.getOrDefault(spaceId, mutableListOf())

        availableSpace.first { it.userId == id }
            .copy(joinedAt = System.currentTimeMillis())
        println("joined $spaces")
    }

    fun leave(spaceId: String, id: String): Int {
        val availableSpace = spaces.getOrDefault(spaceId, mutableListOf())

        return (System.currentTimeMillis() - availableSpace.first { it.userId == id }
            .joinedAt).toInt()
    }
}

data class UsersTime(
    val userId: String,
    val createdAt: Long,
    var joinedAt: Long = 0
)

fun main() {
    val timeSpend = TimeSpend()

    timeSpend.create("xyz", "1")
    timeSpend.create("xyz", "1")
}