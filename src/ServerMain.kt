import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import java.util.Scanner
import kotlin.concurrent.thread

fun main() {
    val port = 1337

    val server = ServerSocket(port)
    println("Server is running @ port {$port}")

    while(true) {
        val client = server.accept()
        println("Client connected! IP: {${client.localAddress.hostAddress}, Port: {${client.port}}")

        thread { ClientHandler(client).run() }
    }
}

class ClientHandler(client: Socket) {

    private val writer: OutputStream = client.getOutputStream()
    private val reader: Scanner = Scanner(client.getInputStream())
    private var running: Boolean = false

    fun run() {

        running = true
        //val welcomeMessage = "**** Hello! ****\n" + "Welcome to the chat server.\n" + "Go ahead and write something!"
        //println("Sending hello message!")
        //socketWrite(welcomeMessage)

        reader.useDelimiter("\n")

        while(running) {
            try {
                val message = reader.next()
                println(message)
            }catch(_: Exception) {

            }
        }
    }

    private fun socketWrite(message: String) {
        writer.write((message + "\n").toByteArray(Charset.defaultCharset()))
        println("'$message'")
    }
}