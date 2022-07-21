package algoexpert

import java.util.*

class Node(name: String) {
    val name: String = name
    val children = mutableListOf<Node>()

    fun breadthFirstSearch(): List<String> {
        val queue = ArrayDeque<Node>()
        val result = mutableListOf<String>()

        queue.add(this)
        while(queue.size != 0) {
            val currentNode = queue.poll()
            result.add(currentNode.name)
            queue.addAll(currentNode.children)
        }

        return result
    }
}