class MyHashMap() {

    /** Initialize your data structure here. */
    val list = mutableListOf<MutableList<Int>>()


    /** value will always be non-negative. */
    fun put(key: Int, value: Int) {
        if(isExist(key)) {
          val position = list.indexOfLast { it.first() == key }
            list[position][1] = value
        } else {
            list.add(mutableListOf(key, value))
        }

    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    fun get(key: Int): Int {
        val position = list.indexOfLast { it.first() == key }
        if (position != -1) {
            return list[position].last()
        }
        return 0
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    fun remove(key: Int) {
        val position = list.indexOfLast { it.first() == key }
        list.removeAt(position)
    }

    private fun isExist(key: Int): Boolean {
        return list.find { it.first() == key } != null
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * var obj = MyHashMap()
 * obj.put(key,value)
 * var param_2 = obj.get(key)
 * obj.remove(key)
 */