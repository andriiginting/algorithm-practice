class Twitter {

    private val userToTweets = mutableListOf<Pair<Int, Int>>()

    // userId to followers
    private val userToMapFriends = mutableMapOf<Int, MutableList<Int>>()

    fun postTweet(userId: Int, tweetId: Int) {
        userToTweets.add(userId to tweetId)
    }

    fun getNewsFeed(userId: Int): List<Int> {
        return userToTweets.filter { tweets ->
            userToMapFriends[userId]?.contains(tweets.first) == true || tweets.first == userId
        }.map {
            it.second
        }.takeLast(10)
            .reversed()
    }

    /**
     * The user with ID followerId started following the user with ID followeeId.
     */
    fun follow(followerId: Int, followeeId: Int) {
        userToMapFriends[followerId]?.add(followeeId) ?: run {
            userToMapFriends[followerId] = mutableListOf()
            userToMapFriends[followerId]?.add(followeeId)
        }
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        userToMapFriends[followerId]?.remove(followeeId)
    }
}

fun main() {
    val twitter = Twitter()

    //postTweet [1,5]
    twitter.postTweet(1, 5)

    //follow [1,2]
    twitter.follow(1, 2)

    //follow [2,1]
    twitter.follow(2, 1)

    //getNewsFeed 2
    twitter.getNewsFeed(2)

    //postTweet 2,6
    twitter.postTweet(2, 6)

    // getNewsFeed 1
    twitter.getNewsFeed(1)

    //getNewsFeed 2
    twitter.getNewsFeed(2)

    //unfollow 2, 1
    twitter.unfollow(2, 1)

    //getNewsFeed 1
    twitter.getNewsFeed(1)

    //getNewsFeed 2
    twitter.getNewsFeed(2)

    //unfollow 2, 1
    twitter.unfollow(1, 2)

    // getNewsFeed 1
    twitter.getNewsFeed(1)

    //getNewsFeed 2
    twitter.getNewsFeed(2)
}