package leetcode

import java.util.*

class AllOne() {

    private val queue = PriorityQueue<String>()

    fun inc(key: String) {
        queue.add(key)
    }

    fun dec(key: String) {
        queue.remove(key)
    }

    fun getMaxKey(): String {
        return if (queue.isEmpty()) {
            ""
        } else {
            queue.peek()
        }
    }

    fun getMinKey(): String {
        return if (queue.isEmpty()) {
            ""
        } else{
            queue.remove()
        }
    }

}