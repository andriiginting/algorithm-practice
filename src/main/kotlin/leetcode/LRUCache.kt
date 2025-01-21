package leetcode

import kotlin.collections.LinkedHashMap

fun main() {

}

class LRUCache(private  val capacity: Int): LinkedHashMap<Int, Int>(capacity, 0.75F, true) {

    private val cache: LinkedHashMap<Int, Int> = object : LinkedHashMap<Int, Int>(capacity) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>): Boolean {
            return size > capacity
        }
    }

    override fun get(key: Int): Int {
        return cache.getOrDefault(key, 0)
    }

    override fun put(key: Int, value: Int): Int? {
        return cache.put(key, value)
    }
}