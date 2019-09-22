package cl.ucn.disc.dsm.chat;

import java.time.LocalDateTime;

public class ChatMessage {

    /**
     * Tiempo en que el mensaje fue recibido por el server
     *
     * */
    private final LocalDateTime timeStamp;

    /***
     * Nombre del Usuario (NOT NULL)
     */

    private final String username;

    /**
     * Mensaje escrito por el de arriba
     */
    private final String message;


    /**
     *
     * @param timeStamp tiempo
     * @param username usuario
     * @param message mensaje
     */
    public ChatMessage(LocalDateTime timeStamp, String username, String message) {
        this.timeStamp = timeStamp;
        this.username = username;
        this.message = message;
    }

    /**
     *
     * @return the date
     */
    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    /**
     *
     * @return the username
     */

    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @return the message
     */

    public String getMessage() {
        return this.message;
    }
}
