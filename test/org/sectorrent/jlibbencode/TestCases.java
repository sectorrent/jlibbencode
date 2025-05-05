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
        System.out.println(new String(a.toBencode()));

    }

    @Test
    public void bytes(){
        BencodeBytes a = new BencodeBytes("Hello World");
        System.out.println(new String(a.toBencode()));

    }
}
