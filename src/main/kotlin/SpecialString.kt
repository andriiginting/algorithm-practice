fun main() {
    println(
        specialStrings(
            listOf(
            "foobarbaz",
                "foo",
                "bar",
                "foobarfoo",
                "baz",
                "foobaz",
                "foofoofoo",
                "foobazar"
            )
        )
    )
}

fun specialStrings(strings: List<String>): List<String> {
    // out put => ["foobarbaz", "foobarfoo", "foobaz", "foofoofoo"]
    if (strings.isEmpty()) {
        return emptyList()
    }

    val result = mutableListOf<String>()
    var currentPrefix = strings[0]
    for (i in 1 until strings.size) {
        if (currentPrefix.contains(strings[i])) {

        }
    }

    return result
}