package com.comexport.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean on 4/15/18.
 */
public class InvalidDataException extends Exception {

    private List< String > messages = new ArrayList<>();

    public void addMessage( String message ) {
        this.messages.add( message );
    }

    public void throwIfExists() throws InvalidDataException {
        if ( !this.messages.isEmpty() ) {
            throw this;
        }
    }

    public List< String > getMessages() {
        return messages;
    }
}
