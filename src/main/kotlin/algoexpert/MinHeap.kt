package algoexpert

import java.util.*

class MinHeap(array: MutableList<Int>) {
    val heap = this.buildHeap(array)
    private val queue = PriorityQueue<Int>()

    fun buildHeap(array: MutableList<Int>): MutableList<Int> {

        return array
    }

    fun siftDown(currentIdx: Int, endIdx: Int, heap: MutableList<Int>) {
        // Write your code here.
    }

    fun siftUp(currentIdx: Int, heap: MutableList<Int>) {
        // Write your code here.
    }

    fun peek(): Int? {
        // Write your code here.
        return -1
    }

    fun remove(): Int? {
        // Write your code here.
        return -1
    }

    fun insert(value: Int) {
        // Write your code here.
    }
}