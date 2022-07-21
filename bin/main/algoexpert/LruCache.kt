package algoexpert

import java.util.LinkedHashMap

fun main() {

}

class LRUCache(private val maxSize: Int): LinkedHashMap<String, Int>(maxSize, 0.75F, true) {
    fun insertKeyValuePair(key: String, value: Int) {
        super.put(key, value)
    }

    fun getValueFromKey(key: String): Int? {
        return super.get(key)
    }

    fun getMostRecentKey(): String? {
        return super.keys.last()
    }

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, Int>?): Boolean {
        return size > maxSize
    }
}