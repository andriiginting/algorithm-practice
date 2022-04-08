package algoexpert

fun main() {

}

val map = mutableMapOf<Char, CharArray>(
    '2' to charArrayOf('a','b','c'),
    '3' to charArrayOf('d','e','f'),
    '4' to charArrayOf('g','h','i'),
    '5' to charArrayOf('j','k','l'),
    '6' to charArrayOf('m','n','o'),
    '7' to charArrayOf('p','q','r', 's'),
    '8' to charArrayOf('t','u','v'),
    '9' to charArrayOf('w','x','y', 'z')
)

fun phoneNumberMnemonics(phoneNumber: String): List<String> {
    // Write your code here.
    val result = mutableListOf<String>()
    dfs(phoneNumber.toCharArray(), result, StringBuilder())
    return result
}

fun dfs(number: CharArray, helper: MutableList<String>, path: StringBuilder) {
    if(path.length == number.size) {
        helper.add(path.toString())
        return
    }

    val nextDigit = number[path.length]
    for(letter in map.getOrDefault(nextDigit, charArrayOf())) {
        path.append(letter)
        dfs(number, helper, path)
        path.deleteCharAt(path.length - 1)
    }
}