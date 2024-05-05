import java.net.InetSocketAddress
import java.net.Socket
import java.nio.charset.Charset
import java.util.Scanner

fun main() {
    print("Please input target port: ")
    val port = readln().toInt()
    val address = "localhost"

    val socket = Socket(address, port)
    println("Socket connected: ${socket.isConnected}")

    val reader = Scanner(socket.getInputStream())
    val writer = socket.getOutputStream()

    while(true) {

        print("You: ")
        var message : String
        message = ""
        try {

        message = readln()

        }catch(ex:Exception) {
            //Todo: do something here, or don't 😂😂
        }
        println(message)
        writer.write((message + "\n").toByteArray(Charset.defaultCharset()))
    }
}