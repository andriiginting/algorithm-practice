

fun main() {
    println(7/2)
}

fun invertedBisection(head: LinkedList): LinkedList {
    if (head.next == null) {
        return head
    }

    var odd = head
    var even = head.next
    val evenHead = even

    while (even?.next != null){
        odd.next = even.next
        odd = odd.next!!
        even.next = odd.next
        even = even.next
    }
    odd = evenHead?.next!!
    return head
}

// This is an input class. Do not edit.
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}
