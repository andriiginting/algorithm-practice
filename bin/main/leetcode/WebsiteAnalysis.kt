package leetcode


fun main() {
}

fun mostVisitedPattern(username: Array<String>, timestamp: IntArray, website: Array<String>): List<String> {
    val map = mutableMapOf<String, MutableList<WebsiteKit>>()
    val result = mutableListOf<String>()

    for (i in 0 until username.size) {
        val currentList = map.getOrDefault(username[i], emptyList<WebsiteKit>()).toMutableList()
        currentList.add(WebsiteKit(timestamp[i], website[i]))
        map[username[i]] = currentList
    }

    return result
}

data class WebsiteKit(
    val timestamp: Int,
    val website: String
)