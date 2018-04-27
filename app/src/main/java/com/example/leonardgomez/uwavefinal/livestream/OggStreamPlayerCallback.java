package com.example.leonardgomez.uwavefinal.livestream;

import com.example.leonardgomez.uwavefinal.livestream.*;


public interface OggStreamPlayerCallback {
    public void playerStarted();

    public void playerStopped();

    public void playerException(Throwable t);
}