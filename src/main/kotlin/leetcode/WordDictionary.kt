package leetcode

class WordDictionary() {

    private val dictionary: MutableList<String> = mutableListOf()

    fun addWord(word: String) {
        dictionary.add(word)
    }

    fun search(word: String): Boolean {
        return dictionary.contains(word)
    }

}
