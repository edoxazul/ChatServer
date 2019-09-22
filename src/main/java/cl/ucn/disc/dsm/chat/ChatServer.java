package cl.ucn.disc.dsm.chat;

import cl.ucn.disc.dsm.chat.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    /**
     * The Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(ChatServer.class);

    /**
     * Representacion de la base de datos
     */

    private final List<ChatMessage> messages = new ArrayList<>();

    /**
     * Puerto en que escuchara el server
     */
    private final int port;


    /**
     * @param chatMessage to add
     * @throws IllegalArgumentException en caso de ser null
     */


    private void add(final ChatMessage chatMessage) {

        if (chatMessage == null) {
            throw new IllegalArgumentException("Can't insert null data");
        }

        LOG.debug("Message Added: {}", chatMessage);

        this.messages.add(chatMessage);
    }

    /**
     * @return the {@link List} of {@link ChatMessage}.
     */
    private List<ChatMessage> get() {
        return this.messages;
    }

    /**
     * The Constructor.
     *
     * @param port to use as server.
     */
    public ChatServer(final int port) {

        // Validacion del puerto
        if (port < 1024 || port > 65535) {
            throw new IllegalArgumentException("Please use a correct port number");
        }

        this.port = port;
    }


    /**
     * Inicio del server
     */
    public void start() throws IOException{
        LOG.debug("Starting the server in port: {}", this.port);

        final ServerSocket serverSocket = new ServerSocket(this.port);

        // Ciclo para atender a los clientes
        while (true) {

            // Cada peticion representa un socket.
            final Socket socket = serverSocket.accept();

            LOG.debug("Connection from: {}", socket);

        }
    }



    /**
     * The Main.
     *
     * @param args
     */
    public static void main(final String args[]) throws IOException {

        LOG.debug("Starting Main ..");

        // Build a chat server.
        final ChatServer chatServer = new ChatServer(9000);
        chatServer.start();

        LOG.debug("The End.");

    }









}
