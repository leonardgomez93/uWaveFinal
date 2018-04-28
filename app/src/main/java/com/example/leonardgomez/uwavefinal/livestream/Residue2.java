package com.example.leonardgomez.uwavefinal.livestream;

import com.example.leonardgomez.uwavefinal.livestream.*;

class Residue2 extends Residue0{

    int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch){
        int i=0;
        for(i=0; i<ch; i++)
            if(nonzero[i]!=0)
                break;
        if(i==ch)
            return (0); /* no nonzero vectors */

        return (_2inverse(vb, vl, in, ch));
    }
}