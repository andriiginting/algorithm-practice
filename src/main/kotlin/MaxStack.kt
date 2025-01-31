import java.util.*

class MaxStack() {

    /** initialize your data structure here. */

    private val stack = ArrayDeque<Int>()
    fun push(x: Int) {
        stack.push(x)
    }

    fun pop(): Int {
       return stack.pop()
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * var obj = MaxStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.peekMax()
 * var param_5 = obj.popMax()
 */