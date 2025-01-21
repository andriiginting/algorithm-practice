class DesignHashMap {

    private val _map = mutableMapOf<Int, Int>()

    fun put(key: Int, value: Int) {
        _map.put(key, value)
    }

    fun get(key: Int): Int {
        return _map[key] ?: -1
    }

    fun remove(key: Int) {
        _map.remove(key)
    }

}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * var obj = MyHashMap()
 * obj.put(key,value)
 * var param_2 = obj.get(key)
 * obj.remove(key)
 */

fun main() {
    val hashMap = DesignHashMap()
}