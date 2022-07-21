class Twitter() {

    /** Initialize your data structure here. */
    private val followers = mutableMapOf<Int, Int>()
    private val feeds = mutableMapOf<Int, List<Int>>()

    //userId as key and tweetId as value
    private val tweet = mutableMapOf<Int, Int>()

    /** Compose a new tweet. */
    fun postTweet(userId: Int, tweetId: Int) {
        tweet[userId] = tweetId
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    fun getNewsFeed(userId: Int): List<Int> {
        return emptyList()
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    fun follow(followerId: Int, followeeId: Int) {
        followers[followeeId] = followerId
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    fun unfollow(followerId: Int, followeeId: Int) {
        followers.remove(followeeId, followerId)
    }

}

fun main() {
    val twitter = Twitter()
}