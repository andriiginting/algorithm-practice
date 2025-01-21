class AuthenticationManager(private val timeToLive: Int) {
    private val authMap = mutableMapOf<String, Int>()

    fun generate(tokenId: String, currentTime: Int) {
        if (authMap.contains(tokenId)) return

        authMap[tokenId] = currentTime + timeToLive
    }

    fun renew(tokenId: String, currentTime: Int) {
        val expiredToken = authMap.getOrDefault(tokenId, currentTime)
        if (expiredToken < currentTime) {
            authMap.remove(tokenId)
        } else {
            authMap[tokenId] = currentTime + timeToLive
        }
    }

    fun countUnexpiredTokens(currentTime: Int): Int {
        val iterator = authMap.iterator()
        while (iterator.hasNext()) {
            val (token, time) = iterator.next()

            if (time <= currentTime) {
                iterator.remove()
            }
        }

        return authMap.size
    }

}