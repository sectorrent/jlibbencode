package org.sectorrent.jlibbencode;

import org.junit.Test;
import org.sectorrent.jlibbencode.variables.BencodeBytes;
import org.sectorrent.jlibbencode.variables.BencodeNumber;

public class TestCases {

    @Test
    public void object(){

    }

    @Test
    public void array(){

    }

    @Test
    public void number(){
        BencodeNumber a = new BencodeNumber(100.2);
        BencodeNumber b = new BencodeNumber();
        b.fromBencode(a.toBencode());

        assert a.equals(b);
    }

    @Test
    public void bytes(){
        BencodeBytes a = new BencodeBytes("Hello World");
        BencodeBytes b = new BencodeBytes();
        b.fromBencode(a.toBencode());

        //assert a.equals(b);
    }
}
