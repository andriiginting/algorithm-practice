package leetcode

import kotlin.collections.LinkedHashMap

fun main() {

}

class LRUCache(private  val capacity: Int): LinkedHashMap<Int, Int>(capacity, 0.75F, true) {

    override fun get(key: Int): Int {
        return super.getOrDefault(key, -1)
    }

    override fun put(key: Int, value: Int): Int? {
        return super.put(key, value)
    }

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
        return size > capacity
    }
}