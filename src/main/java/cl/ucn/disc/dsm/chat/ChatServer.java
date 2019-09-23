/*
 * Copyright 2019 Eduardo Alvarez S
 *
 * For more information See LICENCE.
 * In the root directory
 *
 */


package cl.ucn.disc.dsm.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class ChatServer {

    /** The Logger*/
    private static final Logger LOG = LoggerFactory.getLogger(ChatServer.class);

    /** DB Representation*/
    private final List<ChatMessage> messages = new ArrayList<ChatMessage>();

    /**
     * Server Port
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
     * Constructor.
     * @param port to use as server.
     */
    public ChatServer(final int port) throws IOException {

        // Validacion del puerto
        if (port < 1024 || port > 65535) {
            throw new IllegalArgumentException("Please use a correct port number");
        }

        this.port = port;
    }


    /**
     * Server StartUp
     */
    public void start() throws IOException{
        LOG.debug("Starting the server in port: {}", port);

        final ServerSocket serverSocket = new ServerSocket(port);

        // Ciclo para atender a los clientes
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                final InetAddress address = socket.getInetAddress(); //Adress of the remote connection
                LOG.debug("=======================");
                LOG.debug("Connection from {} in port {}.", address.getHostAddress(), socket.getPort());
                processConnection(socket);
            } catch (IOException e) {
                LOG.error("Connection error", e);
                throw e;
            }

        }
    }

    /**
     * Process the connection.
     *
     * @param socket to use as source of data.
     */
    private static void processConnection(final Socket socket) throws IOException {

        // Reading the inputstream
        final List<String> lines = readInputStreamByLines(socket);

        final String request = lines.get(0);
        LOG.debug("Request: {}", request);

        final PrintWriter pw = new PrintWriter(socket.getOutputStream());




        pw.println("HTTP/1.1 200 OK");
        pw.println("Server: DSM v0.0.1");
        pw.println("Date: " + new Date());
        pw.println("Content-Type: text/html; charset=UTF-8");
        //pw.println("Content-Type: text/plain; charset=UTF-8");
        pw.println();
        pw.println("<html><body>");
        pw.println("<form method=\"post\" action=\"chateo\">");
        pw.println("<br>");
        pw.println("<textarea name=\"comentarios\" rows=\"10\" cols=\"40\"></textarea>");
        pw.println("<br>");
        pw.println("<input type=\"text\" size=\"15\" maxlength=\"30\" value=\"Username\" name=\"username\">");
        pw.println("<input type=\"text\" size=\"15\" maxlength=\"30\" value=\"Message\" name=\"message\">");
        pw.println("<button type=\"button\">Send </button>");
        pw.println("</form>");


//        pw.println("The Date: <strong>" + new Date());
        pw.println("</body></html>");

        pw.flush();

        LOG.debug("Process ended.");

    }


    /**
     * Read all the input stream.
     *
     * @param socket to use to read.
     * @return all the string readed.
     */
    private static List<String> readInputStreamByLines(final Socket socket) throws IOException {

        final InputStream is = socket.getInputStream();

        // The list of string readed from inputstream.
        final List<String> lines = new ArrayList<>();

        // The Scanner
        final Scanner s = new Scanner(is).useDelimiter("\\A");
        LOG.debug("Reading the Inputstream ..");

        while (true) {

            final String line = s.nextLine();
            // log.debug("Line: [{}].", line);

            if (line.length() == 0) {
                break;
            } else {
                lines.add(line);
            }
        }
        // String result = s.hasNext() ? s.next() : "";

        // final List<String> lines = IOUtils.readLines(is, StandardCharsets.UTF_8);
        return lines;

    }












}
