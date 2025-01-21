/**
 * Given two maps in assertThat(map1, map2), write a function that can help the user with the comparison. The signature they gave was:
 *
 * <K,V> ??? diff (Map<K,V> left, Map<K,V> right)
 *
 * ??? denotes any datastructure I would like to create/use.
 */

fun <K, V> assertMap(map1: Map<K, V>, map2: Map<K, V>): Boolean {
    if (map1.size != map2.size) return false

    // loop to check each key. we can use any map1 or map 2 since both has same size
    for ((key, value) in map1) {
        if (!map2.containsKey(key)) {
            return false
        }

        if ((map2[key]?.equals(value)) == false) {
            return false
        }
    }

    // loop to each of value. the best case scenario is use primitive data type like Int, String, etc
    // then we need to check if it is a list. SO we can run the iterable or loop

    return true
}

fun <K, V> assertMap2(map1: Map<K, V>, map2: Map<K, V>): Boolean {
    if (map1.size != map2.size) return false
    val set = mutableSetOf<Pair<K, V>>()
    // loop to check each key. we can use any map1 or map 2 since both has same size
    for ((key, value) in map1) {
        set.add(key to value)
    }

    for ((key, value) in map2) {
        val pairs = key to value
        if (!set.contains(pairs)) {
            return false
        }
    }

    // loop to each of value. the best case scenario is use primitive data type like Int, String, etc
    // then we need to check if it is a list. SO we can run the iterable or loop

    return true
}

fun main() {

    val map1 = mutableMapOf<Int, Int>(
        1 to 1,
        2 to 23
    )

    val map2 = mutableMapOf<Int, Int>(
        1 to 1,
        2 to 23
    )

    println(assertMap2(map1, map2))
}