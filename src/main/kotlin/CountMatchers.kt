class CountMatchers {
    fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
        var count = 0

        val position = when (ruleKey) {
            "type" -> {
                0
            }
            "color" -> {
                1
            }
            else -> {
                2
            }
        }

        items.forEach { items ->
            if (items[position] == ruleValue) {
                count++
            }
        }

        return count
    }

    data class Item(
        val type: String,
        val color: String,
        val name: String
    )
}