package com.example.leonardgomez.uwavefinal.livestream;

import com.example.leonardgomez.uwavefinal.livestream.*;

abstract class FuncResidue{
    public static FuncResidue[] residue_P= {new Residue0(), new Residue1(),
            new Residue2()};

    abstract void pack(Object vr, Buffer opb);

    abstract Object unpack(Info vi, Buffer opb);

    abstract Object look(DspState vd, InfoMode vm, Object vr);

    abstract void free_info(Object i);

    abstract void free_look(Object i);

    abstract int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch);
}