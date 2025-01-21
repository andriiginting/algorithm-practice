package leetcode

class WordDictionary() {

    val dictionary = mutableSetOf<String>()

    fun addWord(word: String) {
        dictionary.add(word)
    }

    fun search(word: String): Boolean {
        word.replace(".", "")

        for (dict in dictionary) {
            return dict.matches(Regex(word))
        }

        return false
    }
}

fun main() {
    val dictionary = WordDictionary()

    dictionary.addWord("bad")
    dictionary.addWord("dad")
    dictionary.addWord("mad")

    println("dictionary: ${dictionary.dictionary}")
    val result = dictionary.search("co")

    println("isFound: $result")
}
