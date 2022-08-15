import java.util.*


fun main() {
    val result = listOf(1,3,4,1,2)
    result.union(list.chunked(3))

    println(result)
}

fun wordBreak(s: String, wordDict: List<String>?): Boolean {
    val wordDictSet: Set<String> = HashSet(wordDict)
    val dp = BooleanArray(s.length + 1)
    dp[0] = true
    for (i in 1..s.length) {
        for (j in 0 until i) {
            if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                dp[i] = true
                break
            }
        }
    }
    return dp[s.length]
}

fun minDeletions(s: String): Int {
    var counter = 0
    val map = mutableMapOf<Char, Int>()

    for(char in s){
        map[char] = map.getOrDefault(char, 0) + 1
    }

    map.forEach { t, u ->
        println("before: $t $u")
    }

    map.toSortedMap()
    val seen = hashSetOf<Char>()

    for((key, value) in map) {
        println("$key $value")
        if(seen.contains(key)) {
            map[key] = value - 1
            counter++
        } else {
            seen.add(key)
        }
    }

    return counter
}

fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val heap = PriorityQueue<Int>()

    for(node in lists) {
        var head = node
        while(head != null) {
            heap.add(head?.`val` ?: 0)
            head = head.next
        }
    }

    var dummy = ListNode(0)
    var head = dummy

    while(!heap.isEmpty()) {
        val nextNode = ListNode(heap.remove())
        head.next = nextNode
    }

    return dummy.next
}

fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
    val list = IntArray(2)
    Arrays.sort<IntArray>(logs) { a: IntArray, b: IntArray -> a[0] - b[0] }
    val groups = hashSetOf<Int>()
    /*
    [20190101,0,1],
    [20190104,3,4],
    [20190107,2,3],
    [20190211,1,5],
    [20190224,2,4],
    [20190301,0,3],
    [20190312,1,2],
    [20190322,4,5]

    n=6
    */
    var earlyTime = -1
    for (log in logs) {
        mutableListOf<Int>().apply {
            add(log[1])
            add(log[2])
        }.let {
            if (groups.addAll(it)) {
                earlyTime = log[0]
            }
        }
    }

    return earlyTime
}

fun longestCommonSubsequence(text1: String, text2: String): Int {
    val list = mutableListOf<String>()
    var longest = 0
    return lcsHelper(0, 0, text1, text2, longest)
}

fun lcsHelper(i: Int, j: Int, str1: String, str2: String, count: Int): Int {
    return if (str1[i].isWhitespace() || str2[j].isWhitespace()) {
        count
    } else if (str1[i] == str2[j]) {
        1 + lcsHelper(i + 1, j + 1, str1, str2, count)
    } else {
        Math.max(
            lcsHelper(i + 1, j, str1, str2, count),
            lcsHelper(i, j + 1, str1, str2, count)
        )
    }
}

fun countPoints(rings: String): Int {
    val red = BooleanArray(10)
    val green = BooleanArray(10)
    val blue = BooleanArray(10)
    var count = 0

    for (i in 1 until rings.length step 2) {
        val color = rings[i - 1]
        val position = rings[i].toString().toInt()
        when (color) {
            'R' -> {
                red[position] = true
            }
            'G' -> {
                green[position] = true
            }
            'B' -> {
                blue[position] = true
            }
        }
    }

    for (i in 0 until red.size) {
        if (red[i] && green[i] && blue[i]) {
            count++
        }
    }

    return count
}

fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    val list = mutableListOf<ListNode>()

    var groups = head
    while (groups != null) {
        list.add(groups)
        groups = groups.next
    }

    for (i in 0 until list.size) {
        if (i / k == 0) {
            list.slice(i..10).reversed()
        }
        println(list[i])
    }

    return head
}

fun reverse(list: MutableList<Int>, i: Int, j: Int) {
    list.slice(i..j)
}

/*
Pick a starting point.
while(Problem is not solved)
    For each path from the starting point.
        check if selected path is safe, if yes select it
        and make recursive call to rest of the problem
        before which undo the current move.
    End For
If none of the move works out, return false, NO SOLUTON.
 */
fun subsets(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<MutableList<Int>>()
    backtracking(nums, result, 0, mutableListOf())

    return result
}


private fun backtracking(nums: IntArray, res: MutableList<MutableList<Int>>, indx: Int, subList: MutableList<Int>) {

    // base case
    if (subList.size <= nums.size) {
        val candidate = subList.toMutableList()
        candidate.sort() // sorting is needed here because it should not contain the duplicate number in the sublist aka candidate
        if (!res.contains(candidate)) {
            res.add(candidate) // if it is suitable just add it to the result list
        }
    }

    for (i in indx until nums.size) if (!subList.contains(nums[i])) { // this if statement is needed to prevent using the same numbers again and again
        subList.add(nums[i])
        backtracking(nums, res, indx + 1, subList)
        subList.removeAt(subList.size - 1) // we need to pop up the element back since the next iteration will contain different combination
    }
}

fun decodeString(s: String): String {
    val numberStack = Stack<Int>()
    val stringStack = Stack<String>()
    var number = 0 // to keep track the number in case has more than 10
    var builder = StringBuilder()

    for (i in 0 until s.length) {
        val input = s.get(i)

        if (input.isDigit()) {
            number = number * 10 + (input - '0')
        } else if (input.isLetter()) {
            builder.append(input)
        } else if (input == '[') {
            numberStack.add(number)
            stringStack.add(builder.toString())

            number = 0
            builder = StringBuilder()
        } else {
            val count = numberStack.pop()
            val duplicateWord = StringBuilder(stringStack.pop())

            var idx = 1
            while (idx <= count) {
                duplicateWord.append(stringStack)
                idx++
            }
            builder = duplicateWord
        }
    }

    return builder.toString()
}

fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    val subs = mutableListOf<MutableList<Int>>()



    return subs
}

fun bulbSwitch(n: Int): Int {
    val list = mutableListOf<Boolean>()
    initBuild(n, list)
    var counter = list.size

    for (i in 1 until n) {
        list[i] = false
        counter--
    }

    return counter
}

private fun initBuild(size: Int, list: MutableList<Boolean>) {
    for (i in 0 until size) {
        list.add(true)
    }
}

fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
    /*
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
    Explanation:
    Window position                Median
    ---------------                -----
    [1  3  -1] -3  5  3  6  7        1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7        3
     1  3  -1  -3 [5  3  6] 7        5
     1  3  -1  -3  5 [3  6  7]       6
     */
    val medians = mutableListOf<Double>()
    val queue = PriorityQueue<Int>()
    var first = 0
    var second = k - 1

    while (second < nums.size) {
        queue.addAll(nums.slice(first..second))
        medians.add(findMedianNumber(queue.toList()))
        first++
        second++
        queue.clear()
    }

    return medians.toDoubleArray()
}

private fun findMedianNumber(number: List<Int>): Double {
    return if (number.size % 2 == 0) {
        ((number[number.size / 2] + number[number.size / 2 - 1]) / 2).toDouble()
    } else {
        number[number.size / 2].toDouble()
    }
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    var size = 0
    var temp = ListNode(0)
    temp.next = head
    var first = temp
    var second = temp

    return head
}

fun lengthOfLongestSubstringV2(s: String): Int {
    var first = 0
    var second = 0
    var maxLength = 0
    val nonRepeating = hashSetOf<Char>()

    while (second < s.length) {
        if (nonRepeating.contains(s[second]).not()) {
            nonRepeating.add(s[second])
            maxLength = Math.max(nonRepeating.size, maxLength)
            second++
        } else {
            nonRepeating.remove(s[first])
            first++
        }
    }


    return maxLength
}

fun longestPalindrome(s: String): String {
    var first = 0
    var second = 0

    for (i in 0 until s.length) {
        val firstScan = scanText(s, i, i)
        val secondScan = scanText(s, i, i + 1)
        val longest = Math.max(firstScan, secondScan)

        if (longest > second - first) {
            first = i - (longest - 1) / 2
            second = i + longest / 2
        }
    }

    return s.substring(first, second + 1)
}

private fun scanText(s: String, first: Int, last: Int): Int {
    var left = first
    var right = last
    if (s.isEmpty() || left < 0 || right > s.length) {
        return 0
    }

    while (s.isNotEmpty() || left > 0 || right <= s.length || s[left] == s[right]) {
        left--
        right++
    }

    return right - left - 1
}

fun isLongestPalindrome(origin: String): Boolean {
    return origin == origin.reversed()
}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val first = mutableListOf<Int>()
    val second = mutableListOf<Int>()

    var firstNode = list1
    while (firstNode?.next != null) {
        first.add(firstNode.`val`)
        firstNode = firstNode.next
    }

    var secondNode = list2
    while (secondNode?.next != null) {
        second.add(secondNode.`val`)
        secondNode = secondNode.next
    }

    val merged = first.zip(second)

    var populateLinkedlist: ListNode? = null

    for (node in merged) {
        val current = ListNode(node.first)
        populateLinkedlist?.next = current
        populateLinkedlist?.next?.next = ListNode(node.second)
    }

    return populateLinkedlist
}

fun maxRepeatedChar(word: String, max: Int): String {
    /*
    given a string of consecutive repeated characters and max value,
    return another string of max repeated characters : for ex: aaaaabbbbbcccdd and max is 2 should be aabbccdd
     */
    val mapWord = mutableMapOf<Char, Int>()
    val builder = StringBuilder()

    for (char in word) {
        mapWord[char] = mapWord.getOrDefault(char, 0) + 1
    }

    for ((key, value) in mapWord) {
        if (value > max) {
            val repeatedWord = key.toString().repeat(max)
            builder.append(repeatedWord)
        } else {
            val repeatedWord = key.toString().repeat(value)
            builder.append(repeatedWord)
        }
    }

    return builder.toString()
}

fun mostVisitedPattern(username: Array<String>, timestamp: IntArray, website: Array<String>): List<String> {
    val map = mutableMapOf<String, TreeMap<Int, String>>() // map name to map of timestamp and website only

    for (i in 0 until timestamp.size) {
        if (!map.containsKey(username[i])) {
            map.putIfAbsent(username[i], TreeMap())
        }
        val treeMap = map.getOrDefault(username[i], TreeMap())
        treeMap[timestamp[i]] = website[i]
        map[username[i]] = treeMap
    }



    return listOf()
}

fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    return nums1.intersect(nums2.toList()).toIntArray()
}

fun isLongPressedName(name: String, typed: String): Boolean {
    var counter = name.length
    val builder = StringBuilder(typed)

    for (i in 0 until name.length) {
        if (typed.contains(name[i])) {
            val idx = builder.indexOf(name[i])
            if (idx != -1) {
                builder.deleteCharAt(idx)
                counter--
            }
        }
    }

    return counter == 0
}

fun firstMissingPositive(nums: IntArray): Int {
    nums.sorted()
    val missingSet = mutableSetOf<Int>()

    for (i in 0 until nums.size) {
        missingSet.add(nums[i])
    }



    return 0
}

fun anagramMappings(nums1: IntArray, nums2: IntArray): IntArray {
    /*
    Input: nums1 = [12,28,46,32,50], nums2 = [50,12,32,46,28]
    Output: [1,4,3,2,0]
    Explanation: As mapping[0] = 1 because the 0th element of nums1 appears at nums2[1], and mapping[1] = 4
    because the 1st element of nums1 appears at nums2[4], and so on.
     */
    val result = mutableListOf<Int>()

    for (i in 0 until nums1.size) {
        if (nums2.contains(nums1[i])) {
            val idx = nums2.indexOf(nums1[i])
            result.add(idx)
        }
    }

    return result.toIntArray()
}

fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    var current = 0
    helperRangeSumBST(root, low, high, current)
    return current
}

private fun helperRangeSumBST(root: TreeNode?, low: Int, high: Int, current: Int): Int {
    if (root != null) {
        if (isWithinRange(low, high, root.`val` ?: 0)) {
            return current + root.`val` ?: 0
        }
        helperRangeSumBST(root.left, low, high, current)
        helperRangeSumBST(root.right, low, high, current)
    }

    return current
}

private fun isWithinRange(low: Int, high: Int, target: Int): Boolean {
    return target in low..high
}

fun removeDuplicates(s: String, k: Int): String {
    /*
    Input: s = "deeedbbcccbdaa", k = 3
    Output: "aa"
    Explanation:
    First delete "eee" and "ccc", get "ddbbbdaa"
    Then delete "bbb", get "dddaa"
    Finally delete "ddd", get "aa"
     */
    val builder = StringBuilder(s)
    val memo = IntArray(builder.length)
    var first = 0

    while (first != builder.length) {
        if (first == 0 || builder[first] !== builder[first - 1]) {
            memo[first] = 1
        } else {
            memo[first] = memo[first - 1] + 1
            if (memo[first] == k) {
                builder.delete(first - k + 1, first + 1)
                first -= k
            }
        }

        first++
    }


    return builder.toString()
}

fun longestConsecutive(nums: IntArray): Int {

    /*
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     */
    nums.sort()
    var counter = 1
    var longest = 1

    for (i in 1 until nums.size) {
        if (nums[i] != nums[i - 1]) {
            val remain = nums[i] - nums[i - 1]
            if (remain == 1) {
                counter++
            } else {
                longest = Math.max(longest, counter)
                counter = 1
            }
        }
    }

    return Math.max(longest, counter)
}

fun wallsAndGates(rooms: Array<IntArray>): Unit {
    /*
    Input: rooms = [
    [2147483647,    -1,          0,         2147483647],
    [2147483647,    2147483647, 2147483647, -1],
    [2147483647,    -1,         2147483647, -1],
    [0,             -1,         2147483647, 2147483647]]

    Output: [
    [3, -1, 0,  1],
    [2, 2,  1,  -1],
    [1, -1, 2,  -1],
    [0, -1, 3,  4]]
     */


}

private fun wallsAndGatesDfs(rooms: Array<IntArray>) {

}

fun findAnagrams(s: String, p: String): List<Int> {
    val sorted = p.toCharArray()
        .sorted().joinToString("")

    var first = 0
    val indexs = mutableListOf<Int>()

    while (first < s.length) {
        var second = first + 1

        while (second <= s.length) {
            if (isFindAnagramValid(sorted, s.substring(first until second))) {
                indexs.add(first)
            }

            second++
        }

        first++
    }

    return indexs
}

private fun isFindAnagramValid(str1: String, str2: String): Boolean {
    return str1 == str2.toCharArray()
        .sorted().joinToString("")
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    /*
    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    val groups = mutableMapOf<String, MutableList<String>>()
    val result = mutableListOf<List<String>>()
    strs.sorted()

    for (word in strs) {
        val sorted = sortedWord(word)

        if (!groups.containsKey(sorted)) {
            groups[sorted] = groups.getOrDefault(sorted, mutableListOf())
        }

        groups[sorted]?.add(word)
    }

    for ((key, value) in groups) {
        result.add(value)
    }

    return result.reversed()
}

private fun sortedWord(str: String): String {
    return str.toCharArray()
        .sorted()
        .joinToString("")
}

fun lengthOfLongestSubstring(s: String): Int {
    var current = ""
    var maxChar = 0
    var first = 0
    var second = first + 1

    while (first < s.length) {
        while (second < s.length) {
            val subs = s.subSequence(first, second)
            if (!hasRepeatingChar(subs.toString())) {
                current = subs.toString()
                maxChar = Math.max(subs.length, maxChar)
            }
            second++
        }
        first++
    }

    return maxChar
}

private fun hasRepeatingChar(s: String): Boolean {
    val hashSet = hashSetOf<Char>()

    for (char in s) {
        if (hashSet.add(char).not()) {
            return true
        }
    }

    return false
}

fun balanceBracket(bracket: String): Boolean {
    /*
    Write a function to check that a String is parenthetically balanced.
For example:
  - "(bar)" - balanced
  - "(()" - not balanced
  - "(())(()" - not balanced

    Given a string, which contains '(' and ')' and some other characters,
    verify if it is a valid parantheses. It will not contains '{' or '['.
     */

    var counter = 0

    for (char in bracket) {
        if (char == '(') {
            counter++
        } else if (char == ')') {
            counter--
        }
    }

    return counter == 0
}

fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<String> {
    val result = mutableListOf<String>()
    val list = mutableListOf<Int>()

    for (i in lower until upper) {
        list.add(i)
    }

    for (i in 0 until nums.size) {
//        if (nums[i + 1] - nums[i] > 2) {
//            val lowerRange = nums[i] + 1
//            val topRange = nums[i + 1] - 1
//
//            if (lowerRange == topRange) {
//                result.add("$lowerRange")
//            } else {
//                result.add("$lowerRange->$topRange")
//            }
//        }

    }

    return result
}

fun matrixBlockSum(mat: Array<IntArray>, k: Int): Array<IntArray> {
    val result = mutableListOf<Int>()

    for (block in mat) {

    }

    return arrayOf(result.toIntArray())
}

fun addToArrayForm(num: IntArray, k: Int): List<Int> {
    val result = mutableListOf<Int>()
    val convertedNumber = convertListToInt(num) + k
    return result.toList()
}

private fun convertIntToList(number: Int, result: MutableList<Int>) {
    val numberInString = "$number"
    numberInString.forEach {
        result.add(Integer.parseInt(it.toString()))
    }
}

private fun convertListToInt(nums: IntArray): Int {
    val size = nums.size - 1
    var result = 0
    var multiply = Math.pow(10 * 1.0, size * 1.0).toInt()

    for (number in nums) {
        val current = number * multiply
        result += current
        multiply /= 10
    }

    var numberInString = ""
    nums.forEach {
        numberInString += it
    }
    return numberInString.toBigInteger().toInt()
}

fun arrayRankTransform(arr: IntArray): IntArray {
    val tranform = arr.toMutableSet().sorted()
    val result = mutableListOf<Int>()

    for (i in 0 until arr.size) {
        val idx = tranform.indexOf(arr[i]) + 1
        result.add(idx)
    }

    return result.toIntArray()
}

fun findPeakElement(nums: IntArray): Int {
    val peekNumber = nums.max() ?: 0
    return nums.indexOf(peekNumber)
}

fun findRestaurant(list1: Array<String>, list2: Array<String>): Array<String> {
    val result = mutableSetOf<String>()

    for (i in 0 until list1.size) {
        if (list2.contains(list1[i])) {
            result.add(list1[i])
        }
    }

    return result.toTypedArray()
}

fun restoreString(s: String, indices: IntArray): String {
    val map = mutableMapOf<Int, Char>()
    val builder = StringBuilder()

    for (i in 0 until s.length) {
        val idx = indices[i]
        map[idx] = s[i]
    }

    //c o d e l e e t
    //0 1 2 3 4 5 6 7
    //4,5,6,7,0,2,1,3
    //leetc

    for (i in 0 until map.size) {
        builder.append(map[i])
    }

    return builder.toString()
}

fun exist(board: Array<CharArray>, word: String): Boolean {
    var occurance = word
    var idx = 0

    board.forEach { row ->
        for (letter in row) {
            if (letter == occurance[idx]) {
                idx++
                occurance = occurance.substring(idx, occurance.length)
            }
        }
    }

    return occurance.isEmpty()
}

fun reverseString(s: CharArray): Unit {
    var idx = 0
    s.reverse()
    for (i in s.size - 1 downTo 0) {

    }
    s.reverse()
}

fun wordPattern(pattern: String, s: String): Boolean {
    val word = s.split(" ").toMutableSet()
    val wordCount = s.split(" ")
    val patternMap = mutableMapOf<Char, Int>()

    if (pattern.length != wordCount.size) {
        return false
    }

    for (code in pattern) {
        patternMap[code] = patternMap.getOrDefault(code, 0) + 1
    }

    return patternMap.keys.size == word.size
}

fun countAndSay(n: Int): String {
    val mapWord = mutableMapOf<Int, String>(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine"
    )

    return "1"
}

fun numTilePossibilities(tiles: String): Int {
    if (tiles.length < 2) {
        return tiles.length
    }
    return 0
}

fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    val list = mutableListOf<Int>()

    for (i in 0 until nums1.size) {
        if (nums1[i] in nums2) {
            val idx = nums2.indexOf(nums1[i])
            val maxSize = nums2.size - 1

            if (maxSize == idx) {
                list.add(-1)
            } else {
                list.add(
                    nextGreater(nums2, idx)
                )
            }
        }
    }

    return list.toIntArray()
}

private fun nextGreater(nums: IntArray, idx: Int): Int {
    for (i in idx until nums.size) {
        if (nums[idx] < nums[i]) {
            return nums[i]
        }
    }

    return -1
}

fun arraysIntersection(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
    val seen = arr1.intersect(arr2.asIterable()).intersect(arr3.asIterable())

    return seen.toList()
}

fun countCharacters(words: Array<String>, chars: String): Int {
    var count = 0
    for (word in words) {
        if (isValidChar(chars, word)) {
            count += word.length
        }
    }
    return count
}

private fun isValidChar(chars: String, word: String): Boolean {
    for (char in word) {
        if (char !in chars) {
            return false
        }
    }

    return true
}

fun alertNames(keyName: Array<String>, keyTime: Array<String>): List<String> {
    val map = mutableMapOf<String, MutableList<Int>>()
    val list = mutableListOf<String>()

    for (i in 0 until keyName.size) {

    }

    return list.toList()
}

fun customSortString(order: String, s: String): String {
    val mapChar = mutableMapOf<Char, Int>()

    for (char in order) {
        mapChar[char] = mapChar.getOrDefault(char, 0) + 1
    }

    for (char in s) {
        mapChar[char] = mapChar.getOrDefault(char, 0) - 1
    }


    val builder = StringBuilder()
    mapChar.keys.forEach {
        builder.append(it)
    }

    return builder.toString()
}

fun deliManipulation(n: String): String {
    var counter = 1
    val builder = StringBuilder()
    for (char in n) {
        if (char.isWhitespace()) {
            counter = 0
            builder.append(" ")
        }

        if (counter == 2) {
            counter++
            continue
        }

        if (counter != 2) {
            builder.append(char)
            counter++
        }
    }

    return builder.toString()
}

fun maxValue(n: String, x: Int): String {
    /*
        n = "99",
        x = 9

        first, convert it into an array [9,9]
        second, create new list with size prev array + 1, because we want to add a new number and try
        to get max value

    */
    var pos = 0
    var neg = 1
    var builder = StringBuilder(n)

    if (n[0] == '-') {
        while (neg < n.length) {
            val value = n[neg] - '0'
            if (value < x) {
                break
            }

            neg++
        }

        builder.insert(neg, x)
    } else {
        while (pos < n.length) {
            val value = n[pos] - '0'
            if (value > x) {
                break
            }

            pos++
        }
        builder.insert(pos, x)
    }

    return builder.toString()
}

fun helperMaxValue(n: String, x: Int): String {
    var current = n.toList()
    val list = mutableListOf<Char>()
        .addAll(current)
    var max = 0

    for (i in 0 until current.size + 1) {

        max = Math.max(0, 0)
    }

    return "$max"
}

fun isNegativeValue(n: String): Boolean {
    return n[0] == '-'
}

fun buyStock(list: IntArray): List<Int> {
    /*
    [7,1,5,3,6,4]
     */

    var current = 0
    var maxProfit = 0
    var result = mutableListOf<Int>()

    for (idx in 1 until list.size) {
        current += list[idx] - list[idx - 1]
        current = Math.max(0, current)
        maxProfit = Math.max(current, maxProfit)
        result.add(maxProfit)
    }

    return result.takeLast(2)
}

fun reverseStringWithChar(s: String, symbols: String): String {
    /*
    for example the text is: deliveryhero
    the output: o*r*e*h*y*r*e*v*i*l*e*d
     */

    val builder = StringBuilder()

    for (idx in s.length - 1 downTo 0) {
        builder.append(s[idx])
        builder.append(symbols)
    }

    return builder.toString()
}

fun reverseStringWithCharEveryPos(s: String, symbols: String, position: Int): String {
    /*
    for example the text is: deliveryhero
    the output: o*r*e*h*y*r*e*v*i*l*e*d
     */

    val builder = StringBuilder()

    for (idx in s.length - 1 downTo 0) {
        if (idx % position == 0) {
            builder.append(symbols)
        } else {
            builder.append(s[idx])
        }
    }

    return builder.toString()
}

fun stringShift(s: String, shift: Array<IntArray>): String {
    var shifted = s
    for (pos in shift) {
        shifted = swapShift(shifted, pos[0], pos[1])
    }

    return shifted
}

private fun swapShift(s: String, i: Int, j: Int): String {
    val swapped = s.toCharArray()
    var temp = swapped[i]
    swapped[i] = swapped[j]
    temp = swapped[i]
    return swapped.joinToString("")
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    val first = nums1.take(m)
    val second = nums2.take(n)

    println(first.plus(second))
}

fun numberOfArithmeticSlices(nums: IntArray): Int {
    return 0
}

private fun replace(delimeter: String, s: String): String {
    val builder = StringBuilder()

    return builder.toString()
}

fun rearrangeElement(list: IntArray): IntArray {
    var idx = 0

    for (i in 0 until list.size) {
        if (list[i] != 0) {
            list[idx++] = list[i]
        }
    }

    for (i in idx until list.size) {
        list[i] = 0
    }

    return list
}

fun closestValue(root: TreeNode?, target: Double): Int {
    val list = mutableListOf<Int>()
    var target = 0
    helper(root, list)

    return Collections.min(list, Comparator<Int> { o1, o2 ->
        return@Comparator if (Math.abs(o1 - target) < Math.abs(o2 - target)) {
            -1
        } else {
            1
        }
    })
}

private fun helper(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) {
        return
    }

    helper(root?.left, list)
    list.add(root.`val`)
    helper(root?.right, list)
}

fun removeDuplicates(s: String): String {
    val builder = StringBuilder()
    val deque = ArrayDeque<Char>()
    //input = abbaca
    //stack = ab

    for (char in s) {
        if (char == deque.peek()) {
            deque.pop()
        } else {
            deque.push(char)
        }

    }

    for (char in deque) {
        builder.append(char)
    }

    return builder.toString()
}

fun isValidSubsequence(array: List<Int>, sequence: List<Int>): Boolean {
    if (array.containsAll(sequence)) {
        return false
    }


    sequence.forEach {
        if (!array.contains(it)) {
            return false
        }
    }

    return true
}

//zalando codility
fun first(n: Int): String {
    val result = StringBuilder()
    for (i in 0 until n) {
        if (i % 2 == 0) {
            result.append("-")
        } else {
            result.append("+")
        }
    }

    return result.toString()
}

fun second(N: Int): Int {
    //add 5 into number
    val additionalNumber = 5

    if (N == 0) {
        return additionalNumber * 10
    }

    var currentMaxValue = Int.MAX_VALUE
    var negativeValue = N / Math.abs(N)
    var number = Math.abs(N)
    var counter = 0
    var position = 1

    while (number > 0) {
        counter++
        number = number / 10
    }

    for (i in 0..counter) {
        val current = ((number / position) * (position * 10)) + (additionalNumber * position) + (number % position)

        if (current * negativeValue > currentMaxValue) {
            currentMaxValue = current * negativeValue
        }

        position *= 10
    }

    return currentMaxValue
}

fun third(A: IntArray): Int {
    if (A.size == 2) {
        return 0
    }

    var slow = A[0] + A[1]
    var slowIndex = 0

    var fast = Int.MAX_VALUE
    var fastIdx = 0

    for (i in 2 until A.size) {
        val temp = A[i - 1] + A[i]
        if (temp < slow) {
            slow = temp
            slowIndex = i - 1
        }

        val tempFast = temp + A[i - 2]
        if (tempFast < fast) {
            fast = tempFast
            fastIdx = i - 2
        }
    }

    val minTwoIndx = slow * 3
    val minThreeIdx = fast * 2

    return if (minTwoIndx == minThreeIdx) {
        Math.min(slowIndex, fastIdx)
    } else {
        if (minTwoIndx < minThreeIdx) {
            slowIndex
        } else {
            fastIdx
        }

    }
}

fun missingElement(nums: IntArray, k: Int): Int {
    /*
    Input: nums = [4,7,9,10], k = 1
    Output: 5
    Explanation: The first missing number is 5.
     */

    if (nums.isEmpty()) {
        return 0
    }
    val cache = IntArray(nums.size + 1)
    val startPoint = nums[0]
    val numbers = mutableListOf<Int>()
    val unknownNumber = mutableListOf<Int>()

    for (i in startPoint..(nums.size * 2)) {
        numbers.add(i)
    }

    println(numbers)
    for (digit in nums) {
        if (!numbers.contains(digit)) {
            unknownNumber.add(digit)
        }
    }

    println(unknownNumber)
    return numbers.elementAt(k).or(0)
}

fun frequencySort(s: String): String {
    val map = mutableMapOf<Char, Int>()
    val builder = StringBuilder()

    for (char in s) {
        map[char] = map.getOrDefault(char, 0) + 1
    }

    map.toSortedMap(Comparator.reverseOrder())
        .forEach { t, u ->
            repeat(u) {
                builder.append("$t")
            }
        }

    return builder.toString()
}

fun validWordAbbreviation(word: String, abbr: String): Boolean {
    /*
    s10n ; word = substitution
    we have first text as s and will take 10 characters from the word that start after the s
    which mean will start from 2nd char until the number
    */
    val stringBuilder = StringBuilder()
    for (i in 0 until abbr.length) {
        if (!abbr[i].isDigit()) {
            stringBuilder.append(abbr[i])
        } else {
            if (abbr[i] == '0') {
                return false
            }

            stringBuilder.append(
                word.substring(i..Character.getNumericValue(abbr[i]))
            )
        }
    }

    return stringBuilder.toString() == word
}

fun uncommonFromSentences(s1: String, s2: String): Array<String> {
    /*
    s1 = "this apple is sweet",
    s2 = "this apple is sour"
    output = ["sweet","sour"]

    1st approach:
    we can use list to store all words from s1
    */

    val map = mutableMapOf<String, Int>()
    val result = mutableListOf<String>()

    val firstSentence = s1.split(" ").toMutableSet() // it will list of string
    val secondSentence = s2.split(" ").toMutableSet()// it will list of string

    for (word in firstSentence) { //[this, apple, is, sweet]

        //this-, apple-, is-, sour
        map[word] = map.getOrDefault(word, 0) + 1
    }

    for (word in secondSentence) {
        if (map.containsKey(word)) {
            map[word] = map.getOrDefault(word, 0) - 1
        } else {
            result.add(word)
        }
    }
    println(map)
    return result.toTypedArray()
}

fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val map = mutableMapOf<Char, Int>()

    for (char in magazine.toCharArray()) {
        map[char] = map.getOrDefault(char, 0) + 1
    }

    for (note in ransomNote.toCharArray()) {
        if (!map.containsKey(note) || map[note]!! <= 0) {
            return false
        }

        map[note] = map.getOrDefault(note, 0) - 1
    }

    return true
}

fun findDisappearedNumbers(nums: IntArray): List<Int> {
    //4,3,2,7,8,2,3,1
    val seen = mutableSetOf<Int>()
    val result = mutableListOf<Int>()
    nums.forEach {
        seen.add(it)
    }

    val max = nums.size

    for (i in 0 until max) {
        if (i in seen || i == 0) {
            //do nothing
        } else {
            result.add(i)
        }
    }

    return result
}

fun isPowerOfTwo(n: Int): Boolean {
    if (n == 1) return true

    var i = 0
    while (i < n) {
        if (Math.pow(2.0, i.toDouble()) == n.toDouble()) {
            return true
        }
    }

    return false
}

fun commonChars(words: Array<String>): List<String> {
    /*
    ["bella","label","roller"]
    output = ["e","l","l"]

    loop the words to get each of word
    "bella"
    loop the map into the map
        if the char is in the map, add to map
        else ignore
    */

    val map = mutableMapOf<Char, Int>()
    val list = mutableListOf<String>()

    for (text in words) {
        for (j in 0 until text.length) {
            val char = text[j]
            if (char in map.keys) {
                map[char] = map.getOrDefault(char, 0) + 1
            }
        }
    }

    println(map)
    return list
}

fun removeElement(nums: IntArray, `val`: Int): Int {
//    nums.toMutableList().filter { it == target }.shuffled()
    nums.binarySearch(`val`)
    for (i in 0 until nums.size) {
        if (nums[i] == `val`) {
            nums.toMutableList().remove(nums[i])
        }
    }

    return nums.size
}

class NodeApple(var `val`: Int) {
    var children: List<NodeApple?> = listOf()
}

fun postorder(root: NodeApple?): List<Int> {
    val list = mutableListOf<Int>()
    if (root == null) {
        return emptyList<Int>()
    }

    var i = 0
    while (i < root.children.size) {
        if (root.children.isNotEmpty()) {
            val lastValue = root.children.last()
            list.add(lastValue!!.`val`)
        }
        i++
    }

    return list
}

fun singleNumbers(nums: IntArray): IntArray {
    val set = mutableSetOf<Int>()

    for (number in nums) {
        if (set.contains(number)) {
            set.remove(number)
        }
        set.add(number)
    }

    return set.toIntArray()
}

fun longestValidParentheses(s: String): Int {
    var count = 0

    val bracketMap = mutableMapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )
    val stack = ArrayDeque<Char>()
    for (bracket in s) {
        if (bracketMap[stack.peek()] == bracket) {
            stack.pop()
            count++
        } else {
            stack.push(bracket)
        }
    }

    return count
}

fun getFolderNames(names: Array<String>): Array<String> {
    val fileMap = mutableMapOf<String, Int>()
    val listOfFolder = mutableListOf<String>()

    for (folder in names) {
        fileMap[folder] = fileMap.getOrDefault(folder, 0) + 1
    }

    return listOfFolder.toTypedArray()
}

private val list = listOf<RomanValue>(
    RomanValue("M", 1000),
    RomanValue("CM", 900),
    RomanValue("D", 500),
    RomanValue("CD", 400),
    RomanValue("C", 100),
    RomanValue("XC", 90),
    RomanValue("L", 50),
    RomanValue("XL", 40),
    RomanValue("X", 10),
    RomanValue("IX", 9),
    RomanValue("V", 5),
    RomanValue("IV", 4),
    RomanValue("I", 1)
)

fun romanToInt(roman: String): Int {

    val mapOfRoman = mapOf(
        'M' to 1000,
        'D' to 500,
        'C' to 100,
        'L' to 50,
        'X' to 10,
        'V' to 5,
        'I' to 1
    )
    /*
    IV = 4
    XL = 40
     */

    var result = 0

    for (i in 0 until roman.length) {
        result += if (i > 0 && mapOfRoman.getOrDefault(roman[i], 0) > mapOfRoman.getOrDefault(roman[i - 1], 0)) {
            mapOfRoman.getOrDefault(roman[i], 0) - 2 * mapOfRoman.getOrDefault(roman[i - 1], 0)
        } else {
            mapOfRoman.getOrDefault(roman[i], 0)
        }
    }

    return result
}

fun intToRoman(num: Int): String {
    var input = num
    val result = StringBuilder()

    for (number in list) {
        val numberOfRoman = input / number.value
        if (numberOfRoman != 0) {
            result.append(number.roman.repeat(numberOfRoman))
        }
        input %= number.value
    }

    return result.toString()
}

data class RomanValue(val roman: String, val value: Int)

fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null && p == null && q == null) {
        return root
    }

    return if (root?.`val`!! > p?.`val`!! && root.`val` > q?.`val`!!) {
        lowestCommonAncestor(root.left, p, q)
    } else if (root.`val` < p.`val` && root.`val` < q?.`val`!!) {
        lowestCommonAncestor(root.right, p, q)
    } else {
        root
    }
}

fun findKthLargest(nums: IntArray, k: Int): Int {
    val size = nums.size - 1
    var swaps = 0
    for (i in 0 until size) {
        for (j in 0 until size - i) {
            if (nums[j] > nums[j + 1]) {
                swapElements(nums, j, j + 1)
            }
        }
    }

    nums.forEach { print("$it, ") }
    println()
    return nums[nums.size - k]
}


fun swapElements(nums: IntArray, i: Int, j: Int) {
    val temp = nums[j]
    nums[j] = nums[i]
    nums[i] = temp
}

fun shortestDistance(wordsDict: Array<String>, word1: String, word2: String): Int {
    val firstIdx = wordsDict.lastIndexOf(word1)
    val secondIdx = wordsDict.lastIndexOf(word2)

    println("$firstIdx $secondIdx")

    return if (firstIdx > secondIdx) {
        firstIdx - secondIdx
    } else {
        secondIdx - firstIdx
    }

}

fun topKFrequent(words: Array<String>, k: Int): List<String> {
    val result = mutableListOf<String>()
    val frequentMap = mutableMapOf<String, Int>()

    for (word in words) {
        frequentMap[word] = frequentMap.getOrDefault(word, 0) + 1
    }

    val sortedMap = frequentMap.toList()
        .sortedByDescending { (key, value) -> key }
        .sortedBy { (key, value) -> value }
        .takeLast(k)
        .reversed()
        .toMap()

    for ((key, value) in sortedMap) {
        result.add(key)
    }
    return result
}

fun myAtoi(s: String): Int {
    s.trim(' ')
    val builder = StringBuilder()
    if (s.isEmpty() || s[0].isLetter()) {
        return 0
    }

    for (i in 0 until s.length) {

        if (s[i] == '-') {
            builder.append(s[i])
        } else if (s[i].isDigit()) {
            builder.append(s[i])
        }
    }

    val temp: Long = builder.toString().toLong()
    var result = builder.toString()
    if (temp > Integer.MAX_VALUE) {
        result = "${Integer.MAX_VALUE}"
    } else if (temp < Integer.MIN_VALUE) {
        result = "${Integer.MIN_VALUE}"
    }

    return result.toInt()
}

fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val result = mutableListOf<Int>()
    if (matrix.isEmpty()) {
        return emptyList()
    }

    var left = 0
    var right = matrix[0].size - 1
    var bottom = matrix.size - 1
    var top = 0
    val size = matrix.size * matrix[0].size

    while (result.size <= size) {

        for (i in left until right) {
            result.add(matrix[top][i])
        }
        top++

        for (i in top until bottom) {
            result.add(matrix[i][right])
        }
        right--

        for (i in right until left) {
            result.add(matrix[bottom][i])
        }
        bottom--

        for (i in bottom until top) {
            result.add(matrix[i][left])
        }
        left++
    }

    return result
}

data class VisitedWebsite(
    val username: String,
    val timestamp: Int,
    val website: String
)

fun maxNumberOfBalloons(text: String): Int {
    val pattern = "balloon"
    val map = mutableMapOf<Char, Int>()

    for (char in text) {
        if (pattern.contains(char)) {
            map[char] = map.getOrDefault(char, 0) + 1
        }
    }

    return map.values.sum() / 7
}

fun fib(n: Int): Int {
    return fibMemoization(n, listOf(n + 1))
}

private fun fibMemoization(n: Int, memo: List<Int>): Int {
    if (n == 0 || n == 1) {
        return n
    }

    if (memo[n] == 0) {
        memo[n] == fibMemoization(n - 1, memo) + fibMemoization(n - 2, memo)
    }

    return memo[n]
}

fun addBinary(a: String, b: String): String {
    val firstValue = Integer.parseInt(a, 2)
    val secondValue = Integer.parseInt(b, 2)
    firstValue.shl(1)
    return "$firstValue"
}

fun validPalindromeII(s: String): Boolean {
    var first = 0
    val last = s.length - 1

    while (first < last) {
        if (isValidPalindromeII(s, first)) {
            return true
        }
        first++
    }

    return false
}

fun isValidPalindromeII(s: String, position: Int): Boolean {
    if (s == s.reversed()) {
        return true
    }

    val char = s[position]
    val word = s.trim(char)
    return word.reversed() == word
}

fun removeDuplicates(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }

    var count = 0
    for (i in 1 until nums.size) {
        if (nums[i] == nums[i - 1]) {
            nums.drop(nums[i])
            count++
        }
    }


    return count + 1
}

fun sortedSquares(nums: IntArray): IntArray {
//    nums.map { number -> number * number }

    for (i in 0 until nums.size) {
        nums[i] = nums[i] * nums[i]
    }
    nums.sort()
    return nums
}

fun majorityElement(nums: IntArray): List<Int> {
    /*
    Input: nums = [3,2,3]
    Output: [3]

    Input: nums = [1,2]
    Output: [1,2]
     */
    val frequency = if (nums.size / 3 != 0) nums.size / 3 else 1
    val mapFrequency = mutableMapOf<Int, Int>()
    val result = mutableListOf<Int>()

    println(frequency)
    for (number in nums) {
        mapFrequency[number] = mapFrequency.getOrDefault(number, 0) + 1
        if (mapFrequency.getOrDefault(number, 0) > frequency) {
            result.add(number)
        }
    }


    return result
}

fun findAndReplacePattern(words: Array<String>, pattern: String): List<String> {
    /*
    Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
    Output: ["mee","aqq"]
    Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
    "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
     */

    val patternMap = mutableMapOf<Char, Int>()
    pattern.forEach { char ->
        patternMap[char] = patternMap.getOrDefault(char, 0) + 1
    }

    val result = mutableListOf<String>()
    for (word in words) {
        if (isMatchPattern(word, patternMap)) {
            result.add(word)
        }
    }

    return result
}

private fun isMatchPattern(s: String, patternMap: MutableMap<Char, Int>): Boolean {
    val map = mutableMapOf<Char, Int>()
    s.forEach { char ->
        map[char] = map.getOrDefault(char, 0) + 1
    }

    return map.keys == patternMap.keys
}


fun convertToTitle(columnNumber: Int): String {
    /*
    Input: columnNumber = 1
    Output: "A"

    Input: columnNumber = 28
    Output: "AB"

    Input: columnNumber = 701
    Output: "ZY"

    Input: columnNumber = 2147483647
    Output: "FXSHRXW"
     */

    var number = columnNumber
    val builder = StringBuilder()

    while (number > 0) {
        number--
        builder.append('A' + number % 26)
        number /= 26
    }

    builder.reverse()
    return builder.toString()
}

fun balancedStringSplit(s: String): Int {
    var count = 0
    var balancer = 0

    for (char in s) {
        if (char == 'R') {
            balancer += 1
        } else if (char == 'L') {
            balancer -= 1
        }

        if (balancer == 0) {
            count++
        }
    }

    return count
}

fun decompressRLElist(nums: IntArray): IntArray {
    val result = mutableListOf<Int>()

    for (i in 0 until nums.size step 2) {
        val list = mutableListOf<Int>()
        for (j in 0 until nums[i]) {
            list.add(nums[i + 1])
        }

        result.addAll(list)
    }

    return result.toIntArray()
}

fun numIdenticalPairs(nums: IntArray): Int {

    /*
    Input: nums = [1,2,3,1,1,3]
    Output: 4
    (0,3) = > [1,1]
    (0,4) => [1,1]
    (3,4) => [1,1]
    (2,5) => [3,3
    Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
     */

    var answer = 0
    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
        answer += map[num]?.minus(1) ?: 0
    }
    return answer
}

fun shuffle(nums: IntArray, n: Int): IntArray {
    val result = intArrayOf(2 * n)
    for (i in 0 until nums.size) {
        result[2 * i] = nums[i]
        result[2 * i + 1] = nums[n + 1]
    }

    return result
}

fun runningSum(nums: IntArray): IntArray {
    for (i in 1 until nums.size) {
        nums[i] += nums[i - 1]
    }

    return nums
}

fun defangIPaddr(address: String): String {
    return address.replace(".", "[.]")
}

fun globMatching(fileName: String, pattern: String): Boolean {

//    val sample = "bcdefg"
//    val pattern = "*e?g"

    val matching = ArrayDeque<Char>()
    val cache = mutableListOf<Char>()


    for (i in fileName.length - 1 downTo 0) {
        matching.add(fileName[i])
    }

    for (i in 0 until pattern.length) {
        when (pattern[i]) {
            '*' -> {

            }
            '?' -> {
                matching.pop()
            }
            else -> {
                if (pattern[i] == matching.peekFirst()) {
                    matching.pop()
                }
            }
        }
    }

    return matching.isEmpty()
}


fun minimumCharactersForWords(words: List<String>): List<Char> {
    /*
    words = ["this", "that", "did", "deed", "them!", "a"]
    this => t,h,i,s (t = 1, h=1, i=1, s=1)
    that => t,h,a,t (t = 2, h = 1, a=1)
    did => d,i,d (d=2, i=1)
    deed => d,e,e,d (d=2, e=2)
    them! => t,h,e,m,!(t=1, h=1, e=1, m=1, !=1)
    a => a (a=1)

    t = 2
    h = 1
    i = 1
    s = 1
    d = 2
    e = 2
    m = 1
    ! = 1
    a = 1
    output = ["!", "a", "d", "d", "e", "e", "h", "i", "m", "s", "t", "t"]
     */

    val maxCharacter = mutableMapOf<Char, Int>()

    for (word in words) {
        val minimumChar = getWordsMap(word)
        updateCharacter(minimumChar, maxCharacter)
    }

    val result = mutableListOf<Char>()
    for ((character, frequency) in maxCharacter) {
        for (i in 0 until frequency) {
            result.add(character)
        }
    }

    return result
}

private fun getWordsMap(word: String): MutableMap<Char, Int> {
    val minimumChar = mutableMapOf<Char, Int>()
    for (char in word) {
        minimumChar[char] = minimumChar.getOrDefault(char, 0) + 1
    }

    return minimumChar
}

private fun updateCharacter(current: MutableMap<Char, Int>, maximum: MutableMap<Char, Int>) {
    for ((char, number) in current) {
        if (char in maximum) {
            maximum[char] = Math.max(number, maximum[char]!!)
        } else {
            maximum[char] = number
        }
    }
}

fun countPrimes(n: Int): Int {
    var result = 0
    if (n == 0 || n == 1) {
        return 0
    }

    for (i in 2..n / 2) {
        if (i % i == 0) {
            result += 1
        }
    }

    return result
}

fun isMonotonic(array: List<Int>): Boolean {
    var isNonDecrease = true
    var isNonIncrease = true
    for (i in 1 until array.size) {
        if (array[i] > array[i - 1]) {
            isNonIncrease = false
        }

        if (array[i] < array[i - 1]) {
            isNonDecrease = false
        }
    }
    return isNonDecrease || isNonIncrease
}

fun plusOne(digits: IntArray): IntArray {

    /*
    input = [9,9]
    output = [1,0,0]
     */
    val n: Int = digits.size
    for (i in n - 1 downTo 0) {
        if (digits[i] < 9) {
            digits[i]++
            return digits
        }
        digits[i] = 0
    }
    val arr = IntArray(n + 1)
    arr[0] = 1
    return arr
}

fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {
    /*
    input = [2,1,2,2,2,3,4,2]
    move = 2

    result = [1,3,4,2,2,2,2,2]
     */

    var first = 0
    var last = array.size - 1

    while (first < last) {
        while (first < last && array[last] == toMove) {
            last--
        }

        if (array[first] == toMove) {
            swap(first, last, array)
        }

        first++
    }


    return array
}

private fun swap(first: Int, last: Int, array: MutableList<Int>) {
    array[first] = array[last].also { array[last] = array[first] }
}

fun maxSubArray(nums: IntArray): Int {
//    var subArray = Integer.MIN_VALUE
//
//    for (i in 0 until nums.size){
//        var current = 0
//        for (j in i until nums.size){
//            current += nums[j]
//            subArray = Math.max(subArray, current)
//        }
//    }

    //kadanes algorithm

    var subArray = nums[0]
    var current = nums[0]

    for (i in 1 until nums.size) {
        current = Math.max(nums[i], current + nums[i])
        subArray = Math.max(subArray, current)
    }

    return subArray
}

fun letterCombinations(digits: String): List<String> {

    /*
    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */

    val keyword = mutableMapOf<Char, String>(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )
    val combination = mutableSetOf<String>()
    digits.forEach { digit ->
        combine(
            keyword.getOrDefault('2', ""),
            keyword.getOrDefault('3', ""),
            combination
        )
    }
    return combination.toMutableList()
}

private fun combine(first: String, second: String, set: MutableSet<String>) {
    for (i in 0 until first.length) {
        for (j in 0 until second.length) {
            set.add("${first[i]}${second[j]}")
        }
    }
}

fun firstUniqChar(s: String): Int {
    val unique = mutableMapOf<Char, Int>()

    for (letter in s) {
        unique[letter] = unique.getOrDefault(letter, 0) + 1
    }

    for (i in 0 until s.length) {
        if (unique[s[i]] == 1) {
            return i
        }
    }

    return -1
}

fun missingNumber(nums: IntArray): Int {
    val set = mutableSetOf<Int>()

    for (number in nums) {
        set.add(number)
    }

    val expectedNumber = nums.size + 1
    for (i in 0 until expectedNumber) {
        if (set.contains(i).not()) {
            return i
        }
    }

    return -1
}

fun numIslands(grid: Array<CharArray>): Int {
    /*
    Input: grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    Output: 1

    Input: grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    Output: 3
     */

    if (grid.isEmpty()) {
        return 0
    }

    var numberIsland = 0
    val row = grid.size
    val column = grid[0].size

    for (r in 0 until row) {
        for (c in 0 until column) {
            if (grid[r][c] == '1') {
                ++numberIsland
                dfs(grid, r, c)
            }
        }
    }

    return numberIsland
}

private fun dfs(grid: Array<CharArray>, r: Int, c: Int) {
    val row = grid.size
    val column = grid[0].size

    if (c >= row || c >= column || r < 0 || c < 0 || grid[r][c] == '0') {
        return
    }

    grid[r][c] = '0'
    dfs(grid, r - 1, c)
    dfs(grid, r + 1, c)
    dfs(grid, r, c - 1)
    dfs(grid, r, c + 1)
}

fun lengthLongestPath(input: String): Int {
    /*
    Input: input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
    Output: 32
    Explanation: We have two files:
   "dir/subdir1/file1.ext" of length 21
    "dir/subdir2/subsubdir2/file2.ext" of length 32.
    We return 32 since it is the longest absolute path to a file.
     */
    return 0
}

fun canConvert(str1: String, str2: String): Boolean {
    /*
    Input: str1 = "aabcc", str2 = "ccdee"
    Output: true
    Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.

    Input: str1 = "leetcode", str2 = "codeleet"
    Output: false
    Explanation: There is no way to transform str1 to str2.
     */
    return false
}

fun singleNumber(nums: IntArray): Int {
    val map = mutableMapOf<Int, Int>()

    for (i in nums) {
        map[i] = map.getOrDefault(map[i], 0) + 1
    }

    for (i in nums) {
        if (map[i] == 1) {
            return i
        }
    }

    return 0
}

fun calculateII(s: String): Int {
    val stack = Stack<Int>()
    val result = 0

    s.trim()
    for (calc in s) {

    }

    return result
}

fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
    /*
    Input: accounts = [
    ["John","johnsmith@mail.com","john_newyork@mail.com"],
    ["John","johnsmith@mail.com","john00@mail.com"],
    ["Mary","mary@mail.com"],
    ["John","johnnybravo@mail.com"]
    ]
    Output: [
    ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
    ["Mary","mary@mail.com"],
    ["John","johnnybravo@mail.com"]
    ]
    Explanation:
    The first and third John's are the same person as they have the common email "johnsmith@mail.com".
    The second John and Mary are different people as none of their email addresses are used by other accounts.
    We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
    ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
     */
    val accountsMap = mutableMapOf<String, MutableList<String>>()

    for (acc in accounts) {
        accountsMap[acc.first()] = getEmail(acc)
    }
    accountsMap.mapValues { entry ->

    }
    return emptyList()
}

private fun getEmail(accounts: List<String>): MutableList<String> {
    val acc = mutableListOf<String>()

    for (i in 1 until accounts.size) {
        acc.add(accounts[i])
    }
    return acc
}

fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    for (block in matrix) {
        if (block.any { it == target }) {
            return true
        }
    }
    return false
}

fun searchRange(nums: IntArray, target: Int): IntArray {
    return intArrayOf(nums.indexOfFirst { it == target }, nums.lastIndexOf(target))
}

fun simplifyPath(path: String): String {
    /*
    Input: path = "/home/"
    Output: "/home"
    Explanation: Note that there is no trailing slash after the last directory name.
     */

    val stack = Stack<String>()
    for (dir in path.split("/")) {
        if (dir.isEmpty() || dir == ".") {
            continue
        } else if (dir == "..") {
            if (stack.isNotEmpty()) {
                stack.pop()
            }
        } else {
            stack.push(dir)
        }
    }

    val finalPath = StringBuilder()
    for (directory in stack) {
        finalPath.apply {
            append("/")
            append(directory)
        }
    }
    return if (finalPath.isNotEmpty()) finalPath.toString() else "/"
}

fun minRemoveToMakeValid(s: String): String {
    /*
    Input: s = "a)b(c)d"
    Output: "ab(c)d"

    Input: s = "(a(b(c)d)"
    Output: "a(b(c)d)"
     */
    val bracketMap = mutableMapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )
    val stack = ArrayDeque<Int>()

    for (letter in s) {

    }

    return s
}

fun minAddToMakeValid(s: String): Int {
    val bracketMap = mutableMapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )
    val stack = ArrayDeque<Char>()
    for (bracket in s) {
        if (bracketMap[stack.peekFirst()] == bracket) {
            stack.pop()
        } else {
            stack.push(bracket)
        }
    }
    return stack.size
}

fun isValid(s: String): Boolean {

    /*
    Input: s = "()[]{}"
    Output: true
     */

    val bracketMap = mutableMapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )
    val stack = ArrayDeque<Char>()
    for (bracket in s) {
        if (bracketMap[stack.peekFirst()] == bracket) {
            stack.pop()
        } else {
            stack.push(bracket)
        }
    }

    return stack.isEmpty()
}

fun removeDuplicateLetters(s: String): String {
/*
Input: s = "cbacdcbc"
    Output: "acdb"
 */

    val duplicate = mutableListOf<Char>()
    val seen = mutableSetOf<Char>()
    for (char in s) {
        if (seen.contains(char)) {
            duplicate.add(char)
        } else {
            seen.add(char)
        }
    }

    return seen.sorted()
        .joinToString("")
}

fun findDuplicates(nums: IntArray): List<Int> {
    val seen = mutableSetOf<Int>()
    val duplicate = mutableListOf<Int>()


    for (number in nums) {
        if (seen.contains(number)) {
            duplicate.add(number)
        } else {
            seen.add(number)
        }
    }

    return duplicate
}

fun maxValues(n: String, x: Int): String {
    var isNegative = false
    var number = n
    if (number[0] == '-') {
        isNegative = true
    }

    for (i in 0 until n.length) {
        var current = n[i] - '0'
        println("current $current")

        if (isNegative.not() && current < x || isNegative && current > x) {
            return ""
        }
    }

    return if (isNegative) "-" else ""
}

fun expressiveWords(s: String, words: Array<String>): Int {
    /*
    Input: s = "heeellooo", words = ["hello", "hi", "helo"]
    Output: 1
    Explanation:
    We can extend "e" and "o" in the word "hello" to get "heeellooo".
    We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
     */
    var result = 0
    for (word in words) {
        if (word.contentEquals(s)) {
            result += 1
        }
    }
    return result
}

fun validPalindrome(s: String): Boolean {
    for (i in 0 until s.length) {
        val words = s.trim().toMutableList()
        words.removeAt(i)
        if (isValids(words.joinToString(""))) {
            return true
        }
    }

    return false
}

private fun isValids(s: String): Boolean {
    return s == s.reversed()
}

fun moveZeroes(nums: IntArray): Unit {
    val movedZeroes = nums
    var nonZeroPosition = 0

    for (i in 0 until nums.size) {
        if (movedZeroes[i] != 0) {
            movedZeroes[nonZeroPosition++] = movedZeroes[i]
        }
    }

    for (i in nonZeroPosition until nums.size) {
        movedZeroes[i] = 0
    }
}

fun balancedBrackets(str: String): Boolean {
    val map = mutableMapOf<Char, Char>(
        '(' to ')',
        '[' to ']',
        '{' to '}'
    )
    val deque = ArrayDeque<Char>()

    str.toList().forEach {
        deque.push(it)
        if (it in map.keys) {
            deque.pop()
        }
    }

    return deque.isEmpty()
}

fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    /*
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]
    Explanation: [4,9] is also accepted.
     */

    val sets = mutableSetOf<Int>()

    for (num in nums1) {
        sets.add(num)
    }

    val result = mutableListOf<Int>()

    for (i in nums2) {
        if (sets.contains(i)) {
            result.add(i)
            sets.remove(i)
        }
    }

    return result.toIntArray()
}

fun intersectionII(nums1: IntArray, nums2: IntArray): IntArray {
    /*
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]
    Explanation: [4,9] is also accepted.
     */

    val sets = mutableSetOf<Int>()

    for (num in nums2) {
        sets.add(num)
    }

    val result = mutableListOf<Int>()

    for (i in nums1) {
        if (sets.contains(i)) {
            result.add(i)
            sets.remove(i)
        }
    }

    return result.toIntArray()
}

fun isAnagram(s: String, t: String): Boolean {
    return Arrays.equals(
        s.chars().sorted().toArray(),
        t.chars().sorted().toArray()
    )
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    Arrays.sort(intervals) { a, b ->
        Integer.compare(a[0], b[0])
    }

    val merged = ArrayDeque<IntArray>()

    for (interval in intervals) {
        if (merged.isEmpty() || merged.last[1] < interval[0]) {
            merged.add(interval)
        } else {
            merged.last[1] = Math.max(merged.last[1], interval[1])
        }
    }

    return merged.toArray(arrayOf(intArrayOf(merged.size)))
}

fun search(nums: IntArray, target: Int): Int {
    return nums.indexOf(target)
}

fun reverseList(head: ListNode?): ListNode? {
    if (head?.next == null) {
        return head
    }

    val reversed = reverseList(head.next)
    head.next!!.next = head
    head.next = null
    return reversed
}

fun twoSum2(nums: IntArray, target: Int): IntArray {

    val map = mutableMapOf<Int, Int>()

    for (i in 0 until nums.size) {
        val remain = target - nums[i]

        if (map.containsKey(remain)) {
            return intArrayOf(map[remain]!!, i)
        }

        map[nums[i]] = i
    }

    return intArrayOf()
}

fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    val words = paragraph.toLowerCase()
        .splitToSequence(" ")
        .toList()


    return ""
}

fun backspaceCompare(s: String, t: String): Boolean {
    val dequeS = ArrayDeque<Char>()
    val dequeT = ArrayDeque<Char>()

    s.toList().forEach {
        if (it == '#') {
            if (dequeS.isNotEmpty()) {
                dequeS.pop()
            }
        } else {
            dequeS.push(it)
        }
    }

    t.toList().forEach {
        if (it == '#') {
            if (dequeT.isNotEmpty()) {
                dequeT.pop()
            }
        } else {
            dequeT.push(it)
        }
    }

    return dequeS.joinToString("") == dequeT.joinToString("")
}

fun filterRestaurants(restaurants: Array<IntArray>, veganFriendly: Int, maxPrice: Int, maxDistance: Int): List<Int> {
    val restaurant = mutableListOf<Restaurant>()

    restaurants.forEach {
        restaurant.add(
            Restaurant(it[0], it[1], it[2], it[3], it[4])
        )
    }

    restaurant.filter { it.veganFriendly == veganFriendly && it.price <= maxPrice && it.distance <= maxDistance }

    return restaurant.map { it.id }
}


data class Restaurant(
    val id: Int,
    val rating: Int,
    val veganFriendly: Int,
    val price: Int,
    val distance: Int
)

fun fizzBuzz(n: Int): List<String> {
    val minstack = ArrayDeque<Int>()


    val solutions = mutableListOf<String>()

    for (i in 1..n) {
        if (i / 3 == 0) {
            solutions.add("Fizz")
        } else if (i / 5 == 0) {
            solutions.add("Buzz")
        } else if (i / 5 == 0 && i / 3 == 0) {
            solutions.add("FizzBuzz")
        } else {
            solutions.add("$i")
        }
    }

    return solutions
}

fun encode(num: Int): String {
    /*

    Assume g(n) = "1" + f(n)
    we can find:
    g(0) = "1" g(1) = "10" g(2) = "111" g(3) = "100" g(4) = "101" g(5) = "110" g(6) = "111"

    Now everything is obvious:

    g(n) = binary(n + 1)
    "1" + f(n) = binary(n + 1)
    f(n) = binary(n + 1).substring(1)
     */
    return Integer.toBinaryString(num + 1).substring(1)
}

fun isPalindrome(x: Int): Boolean {
    return "$x" == "$x".reversed()
}

fun findLengthOfShortestSubarray(arr: IntArray): Int {

    //[1,2,3,10,4,2,3,5]
    /*
    The shortest subarray we can remove is [10,4,2] of length 3.
    The remaining elements after that will be [1,2,3,3,5] which are sorted.
    Another correct solution is to remove the subarray [3,10,4].
     */
    val removed = mutableListOf<Int>()

    for (i in 0 until arr.size - 1) {
        if (arr[i] > arr[i + 1]) {
            println(arr[i])
            removed.add(arr[i])
        }
    }

    return removed.size
}

fun isSubsequence(s: String, t: String): Boolean {
    return t.subSequence(0..t.length) == s
}

fun next(`val`: Int): Double {
    val streams = mutableListOf<Int>()
    streams.add(`val`)
    streams.add(10)
    streams.add(3)
    streams.add(5)
    return (streams.takeLast(3).sum().toDouble() / streams.size)
}

private fun sum(size: Int, list: List<Int>): Int {
    var sums = 0

    return list.takeLast(size).sum()
}

fun reverseWords(s: String): String {

    val sentence = s.trim()
    val words = Arrays.asList(sentence.split("\\s+"))

    return words.apply {
        reverse()
    }.joinToString(" ")

}

fun isPalindrome(s: String): Boolean {
    val sentence = s.toLowerCase()
        .replace(" ", "")
        .replace(Regex("[^A-Za-z0-9 ]"), "")
    return sentence == sentence.reversed()
}

fun addStrings(num1: String, num2: String): String {
    val sum = num1.toBigInteger() + num2.toBigInteger()
    return "$sum"
}

fun twoSums(nums: IntArray, target: Int): IntArray {
    var firstPointer = 0
    var lastPointer = nums.size - 1

    while (firstPointer <= lastPointer) {
        val sum = nums[firstPointer] + nums[lastPointer]

        if (sum > target) {
            lastPointer -= 1
        } else if (sum < target) {
            firstPointer += 1
        } else {
            return intArrayOf(firstPointer, lastPointer)
        }
    }

    return intArrayOf(firstPointer, lastPointer)
}

fun compress(chars: CharArray): Int {
    //["a","a","b","b","c","c","c"]
    var indexSize = 0
    var first = 0

    while (first < chars.size) {
        var last = first

        while (last < chars.size && chars[first] == chars[last]) {
            last++
        }

        chars[indexSize++] = chars[first]

        if (last - first > 1) {
            val count = "$${last - first}"
            for (char in count) {
                chars[indexSize++] = char
            }
        }

        first = last
    }

    return indexSize
}

fun replaces(text: String) {
    val input = text.trim().toList()
    println(input)
}

fun isMatchesWith(text: String): Boolean {
    return text.startsWith("(") && text.endsWith(")")
}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val list = nums1.plus(nums2)
    list.forEach {
        println(it)
    }

    list.sort()

    return getMedian(list)
}

fun getMedian(arr: IntArray): Double {
    val size = arr.size
    return if (arr.size % 2 != 0) {
        arr[size / 2].toDouble()
    } else {
        (arr[(size - 1) / 2] + arr[size / 2]) / 2.0
    }
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    var first = 1
    var last = nums.size - 1
    val pairs = intArrayOf()

    while (first < last) {
        if (nums[first] + nums[last] == target) {
            pairs[0] = first
            pairs[1] = last
        }

        last--
        first++
    }

    return pairs
}

fun licenseKeyFormatting(s: String, k: Int): String {
    val fistGroup = s.substringBefore("-")
    val secondGroup = s.substringAfter("-")
        .replace("-", "")
    val builder = StringBuilder()

    builder.apply {
        append(fistGroup.toUpperCase())
        append("-")
    }

    var second = ""
    for (i in 0 until secondGroup.length) {
    }

    return builder.toString()
}

fun multiply(num1: String, num2: String): String {
    val result = num1.toBigInteger() * num2.toBigInteger()
    return "$result"
}
