class WordDistance(wordsDict: Array<String>) {

    private val dictionary = mutableListOf<String>()
    init {
        dictionary.addAll(wordsDict)
    }
    fun shortest(word1: String, word2: String): Int {
        val first = dictionary.indexOf(word1)
        val second = dictionary.indexOf(word2)

        return if (first > second) {
            first - second
        } else {
            second - first
        }
    }

}

fun main() {
    val dictionary = arrayOf("practice", "makes", "perfect", "coding", "makes")
    val word = WordDistance(dictionary)

    //3 - 0
    println(word.shortest("coding", "practice"))

    //1-3 =>
    println(word.shortest("makes", "coding"))
}