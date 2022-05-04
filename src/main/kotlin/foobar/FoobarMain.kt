package foobar

fun main() {
    val result = elevatorMaintenance(arrayOf("1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"))
    result.map {
        println(it)
    }
}

fun elevatorMaintenance(l: Array<String>): Array<List<String>> {
    val list = mutableListOf<List<String>>()

    for (version in l) {
        list.add(
            version.split(".")
        )
    }
    list.sortWith(Comparator { o1, o2 ->
        var idx = 0
        while (idx < Math.min(o1.size, o2.size)) {
            val parse = Integer.compare(
                Integer.parseInt(o1[idx]),
                Integer.parseInt(o2[idx])
            )

            if (parse != 0) {
                return@Comparator parse
            }
            idx++
        }

        return@Comparator Integer.compare(o1.size, o2.size)
    })

    return arrayOf(
        list.map {
            it.joinToString(".")
        }
    )

}