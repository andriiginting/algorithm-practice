import java.util.ArrayDeque

class CollaborativePlaylist(private val playlists: List<Playlist>) {

    private val deque = ArrayDeque<Song>()

    init {
        initialize()
    }

    fun getNextSong(): Song? {
        if (hasNextSong()) {
            return deque.pop()
        }

        return null
    }

    fun hasNextSong(): Boolean {
        return deque.isNotEmpty()
    }

    private fun initialize() {
        var index = 0

        if (playlists.isEmpty()) return

        playlists.forEach { playlist ->
            
        }
    }
}

interface Playlist {
    fun nextSong(): Song?
    fun hasNextSong(): Boolean
}

data class Song(val name: String)

/**
 * P1 = [a, b]
 * P2 = [c, d, e]
 * playlist = [a,c,b,d,e]
 */