class Trie {

    private val children: Array<Trie?> = arrayOfNulls(26)

    // isEndOfWord is true if the node represents the end of a word.
    private var isEndOfWord: Boolean = false;

    fun insert(word: String) {
        var currentNode = this
        for (char in word) {
            val index = char - 'a' //get the index from ASCII code

            //if the character not exist in the child, initialize new object
            if (currentNode.children[index] == null) {
                currentNode.children[index] = Trie()
            }

            //move trie to the next node -> which mean append to last of childern
            currentNode = currentNode.children[index]!!
        }

        // mark the character as the last of char in the list
        currentNode.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        val currentNode = searchPrefix(word)

        return currentNode != null && currentNode.isEndOfWord
    }

    fun startsWith(prefix: String): Boolean {
        val currentNode = searchPrefix(prefix)

        return currentNode != null
    }

    //helper function to find the prefix character from query
    private fun searchPrefix(query: String): Trie? {
        var currentNode = this
        for (char in query) {
            val index = char - 'a'
            if (currentNode.children[index] == null) {
                //return null if query not exist in the children
                return null
            }

            currentNode = currentNode.children[index]!!
        }

        return currentNode
    }
}

fun main() {
    val trie = Trie()

    //["Trie","insert","search","search","startsWith","insert","search"]
    //[[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
    trie.insert("apple").printObject()
    trie.search("apple").printObject()
    trie.search("app").printObject()
    trie.startsWith("app").printObject()
    trie.insert("app").printObject()
    trie.search("app").printObject()
}

fun Any.printObject(): Any {
    return apply {
        println(this)
    }
}
