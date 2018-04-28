package com.example.leonardgomez.uwavefinal.livestream;

import com.example.leonardgomez.uwavefinal.livestream.*;

public class JOrbisException extends Exception {

    private static final long serialVersionUID = 1L;

    public JOrbisException() {
        super();
    }

    public JOrbisException(String s) {
        super("JOrbis: " + s);
    }
}