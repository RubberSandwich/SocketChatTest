import java.net.Socket
import java.util.*

val commands = ClientCommandHandler()
var connectedServer = Socket()
var currentScreen = ""
var running = true
var chatUser = ChatUser("")

fun main() {

    println("Welcome to RubberSandwich's Not-so-simple Chat Client!™ 😍👌🔥")
    chatUser = initChatUser()
    currentScreen = "Main"
    println("/////////////////////////////////")
    println("Welcome ${chatUser.getUsername()}!")
    println("/////////////////////////////////")
    println("To connect to a server, enter the \"connect\" command and then input the target ip-address and port!")
    println("To see what else you can do, enter the \"help\" command!")

    while(running) {
        print("$currentScreen>")
        val input = readln()
        parseCommand(input)
    }

}
fun parseCommand(input:String) {
    if(commands.getCommands().containsKey(input)) {
        when(input) {
            "help" -> {
                executeHelpCommand()
            }
            "connect" -> {
                executeConnectCommand()
            }
            "exit" -> {
                executeExitCommand()
            }
        }

    }else {
        println("Error: invalid command \"$input\". Please use \"help\" to list available commands!")
    }
}

fun executeHelpCommand() {
    println("Available commands:")
    for (command in commands.getCommands()) {
        println("${command.key} | ${command.value.getHelpText()}")
    }
}

fun executeConnectCommand() {
    if(connectedServer.isConnected) {
        println("Currently connected to a server, please disconnect before creating a new connection!")
        return
    }
    print("Please input target ip-address: ")
    val address = readln()
    print("Please input target port: ")
    val port = readln().toInt()

    try {
        val socket = Socket(address, port)
        println("Socket connected: ${socket.isConnected}")

        val reader = Scanner(socket.getInputStream())
        val writer = socket.getOutputStream()

        //Send user info to server
        val message = "username:" + chatUser.getUsername()
        writer.write(message.encodeToByteArray())
    }catch(ex : Exception) {
        println("Error: An error occurred during attempted socket setup.")
        println(ex.message)
        return
    }
/*
    while(true) {
        print("You: ")
        var message : String
        message = ""
        try {
            message = readln()
        }catch(ex:Exception) {
            //Todo: do something here, or don't 😂😂
        }
        writer.write((message + "\n").toByteArray(Charset.defaultCharset()))
    }

 */
}


fun executeExitCommand() {
    println("Exiting!")
    running = false
}

fun initChatUser() : ChatUser {
    print("Please input your desired username: ")
    val username = readln()
    return ChatUser(username)
}

public class ChatUser(private val username: String) {
    fun getUsername(): String { return username}
}