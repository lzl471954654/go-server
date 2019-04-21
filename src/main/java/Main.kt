import beans.Room
import utils.Connection
import utils.startSendThread
import java.net.ServerSocket
import java.util.concurrent.ConcurrentHashMap

fun main(args: Array<String>) {
    startSendThread()
    val server = ServerSocket(20000)
    while (true){
        val socket = server.accept()
        println("Get a new socket "+socket.toString())
        Connection(socket).start()
    }
}

val roomMap = ConcurrentHashMap<String,Room>()