import java.util.*


class TaskServer {
    fun assignTasks(servers: IntArray, tasks: IntArray): IntArray {
        val availableServer = PriorityQueue<IntArray> { server1, server2 ->
            if (server1[0] == server2[0]) {
                server1[1] - server2[1]
            } else server1[0] - server2[0]
        }

        //timefree, weight of server, index
        val unavailableServer = PriorityQueue(
            Comparator { server1: IntArray, server2: IntArray ->
                if (server1[0] == server2[0]) {
                    return@Comparator if (server1[1] == server2[1]) server1[2] - server2[2] else server1[1] - server2[1]
                }
                server1[0] - server2[0]
            }
        )

        val result = IntArray(tasks.size)

        for (i in servers.indices) {
            availableServer.offer(intArrayOf(servers[i], i))
        }

        //time free tracker
        var timeFree = 0



        for (i in tasks.indices) {
            val duration = tasks[i]

            // Release all servers that have completed their tasks by this second.
            while(unavailableServer.isNotEmpty() && unavailableServer.peek()[0] <= timeFree) {
                val server = unavailableServer.poll()
                availableServer.offer(intArrayOf(server[1], server[2]))
            }

            // assign to task to current server
            if(availableServer.isNotEmpty()){
                val server = availableServer.poll()
                result[timeFree++] = server[1]
                unavailableServer.offer(intArrayOf(i + duration, server[0], server[1]))
            } else {
                val server = unavailableServer.poll()
                result[timeFree++] = server[2]
                unavailableServer.offer(intArrayOf(server[0] + duration, server[1], server[2]))
            }
        }



        return result
    }
}