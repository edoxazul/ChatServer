/*
 * Copyright 2019 Eduardo Alvarez S
 *
 * For more information See LICENCE.
 * In the root directory
 *
 */


import cl.ucn.disc.dsm.chat.ChatServer;
import java.io.IOException;

public final class Main {


    /**
     * The Port
     */
    private static final int PORT = 9000;

    public static void main(final String[] args) throws IOException, IOException {
        ChatServer Server = new ChatServer(PORT); //
        Server.start();

    }
}
