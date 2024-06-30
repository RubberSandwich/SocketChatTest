class ClientCommand(private val helpText:String ){
    public fun getHelpText() : String {
        return helpText
    }
}

public class ClientCommandHandler() {
    private val commands = mapOf(Pair("help",ClientCommand("Prints this message!")),
                                Pair("connect",ClientCommand( "Attempts to connect to a remote server and join the hosted chat room.")),
                                Pair("disconnect",ClientCommand( "Disconnects from a connected chat room.")),
                                Pair("edituser",ClientCommand( "Enters the menu for editing your user information. This is where you can change your name etc.")),
                                Pair("exit",ClientCommand("Exits out of the application. If connected to a chat room you will be disconnected first before shutting down.")),
                                Pair("listusers",ClientCommand("Lists all the active users in a chat room that you are connected to.")))
    public fun getCommands() : Map<String,ClientCommand> {
        return commands
    }
}
