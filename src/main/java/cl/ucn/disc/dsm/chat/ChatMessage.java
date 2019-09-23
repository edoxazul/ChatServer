/*
 * Copyright 2019 Eduardo Alvarez S
 *
 * For more information See LICENCE.
 * In the root directory
 *
 */


package cl.ucn.disc.dsm.chat;

import java.time.LocalDateTime;

public class ChatMessage {

    /**
     * Atributtes of ChatMessage class
     */

    private final LocalDateTime timeStamp; //Time the message was received by the server
    private final String username; //Username (Not null)
    private final String message; //Message written by the user.


    /**
     *ChatMessage constructor
     *
     * @param username user
     * @param message message
     */
    public ChatMessage(String username, String message) {
        this.timeStamp = LocalDateTime.now(); //Current Server Time
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
