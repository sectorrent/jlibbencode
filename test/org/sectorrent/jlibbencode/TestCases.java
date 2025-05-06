package org.sectorrent.jlibbencode;

import org.junit.Test;
import org.sectorrent.jlibbencode.variables.BencodeArray;
import org.sectorrent.jlibbencode.variables.BencodeBytes;
import org.sectorrent.jlibbencode.variables.BencodeNumber;
import org.sectorrent.jlibbencode.variables.BencodeObject;

public class TestCases {

    @Test
    public void object(){
        BencodeObject a = new BencodeObject();
        a.put("a", "HELLO WORLD");
        a.put("b", 100.2);
        BencodeObject b = new BencodeObject();
        b.fromBencode(a.toBencode());

        assert a.equals(b);
    }

    @Test
    public void array(){
        BencodeArray a = new BencodeArray();
        a.add("HELLO WORLD");
        a.add(100.2);
        BencodeArray b = new BencodeArray();
        b.fromBencode(a.toBencode());

        assert a.equals(b);
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

        assert a.equals(b);
    }
}
