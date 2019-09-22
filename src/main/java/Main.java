
import cl.ucn.disc.dsm.chat.ChatMessage;
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


public final class Main {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    /**
     * The Port
     */
    private static final int PORT = 9000;

    /**
     * The Ppal.
     */
    public static void main(final String[] args) throws IOException {

        log.debug("Starting the Main ..");

        // The Server Socket
        final ServerSocket serverSocket = new ServerSocket(PORT);

        // serverSocket.setReuseAddress(true);
        log.debug("Server started in port {}, waiting for connections ..", PORT);

        // Forever serve.
        while (true) {

            // One socket by request (try with resources).
            try (final Socket socket = serverSocket.accept()) {

                // The remote connection address.
                final InetAddress address = socket.getInetAddress();

                log.debug("========================================================================================");
                log.debug("Connection from {} in port {}.", address.getHostAddress(), socket.getPort());
                processConnection(socket);

            } catch (IOException e) {
                log.error("Error", e);
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
        log.debug("Request: {}", request);

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

        log.debug("Process ended.");

    }


//    private static String htmlPage(){
//
//        StringBuffer sb = new StringBuffer();
//
//        sb.append("<html>");
//
//
//
//
//        String string = sb.toString();
//
//        return string;
//    }

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
        log.debug("Reading the Inputstream ..");

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
