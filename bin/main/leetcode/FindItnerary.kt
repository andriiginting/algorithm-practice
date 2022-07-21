package leetcode

import java.util.*

fun main() {
    val result = findItinerary(
        listOf(
            listOf("MUC","LHR"),
            listOf("JFK","MUC"),
            listOf("SFO","SJC"),
            listOf("LHR","SFO")
        )
    )

    println(result)
}

fun findItinerary(tickets: List<List<String>>): List<String> {
    val linkedList = LinkedList<String>()

    for(ticket in tickets){
        linkedList.add(ticket[0])
        linkedList.add(ticket[1])
    }
    return linkedList.toMutableSet().toList()
}

data class Airport(
    val from: String,
    val to: String
)