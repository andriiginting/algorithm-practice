package leetcode

class AllOne() {

    private val map = mutableMapOf<String, Int>()

    fun inc(key: String) {
        map[key] = key.length
    }

    fun dec(key: String) {
        map.remove(key)
    }

    fun getMaxKey(): String {
        return map.maxBy { it.value }?.key.orEmpty()
    }

    fun getMinKey(): String {
        return map.minBy { it.value }?.key.orEmpty()
    }

}