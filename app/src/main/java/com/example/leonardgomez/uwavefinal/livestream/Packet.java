package com.example.leonardgomez.uwavefinal.livestream;

import com.example.leonardgomez.uwavefinal.livestream.*;

/**
 * Created by leonardgomez on 3/4/18.
 */

public class Packet {
    public byte[] packet_base;
    public int packet;
    public int bytes;
    public int b_o_s;
    public int e_o_s;

    public long granulepos;

    /**
     * sequence number for decode; the framing
     * knows where there's a hole in the data,
     * but we need coupling so that the codec
     * (which is in a seperate abstraction
     * layer) also knows about the gap
     */
    public long packetno;
}
