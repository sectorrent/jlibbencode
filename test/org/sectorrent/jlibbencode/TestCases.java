package org.sectorrent.jlibbencode;

import org.junit.Test;
import org.sectorrent.jlibbencode.variables.BencodeArray;
import org.sectorrent.jlibbencode.variables.BencodeBytes;
import org.sectorrent.jlibbencode.variables.BencodeNumber;
import org.sectorrent.jlibbencode.variables.BencodeObject;

import java.util.Arrays;

public class TestCases {

    @Test
    public void object(){
        BencodeObject a = new BencodeObject();
        a.put("a", "HELLO WORLD");
        a.put("b", 100.2);
        BencodeObject b = new BencodeObject();
        b.fromBencode(a.toBencode());

        assert a.equals(b);

        byte[] c = "d1:a11:HELLO WORLD1:bi100.2ee".getBytes();
        b = new BencodeObject();
        b.fromBencode(c);

        assert Arrays.equals(c, b.toBencode());
    }

    @Test
    public void array(){
        BencodeArray a = new BencodeArray();
        a.add("HELLO WORLD");
        a.add(100.2);
        BencodeArray b = new BencodeArray();
        b.fromBencode(a.toBencode());

        assert a.equals(b);

        byte[] c = "l11:HELLO WORLDi100.2ee".getBytes();
        b = new BencodeArray();
        b.fromBencode(c);

        assert Arrays.equals(c, b.toBencode());
    }

    @Test
    public void number(){
        BencodeNumber a = new BencodeNumber(100.2);
        BencodeNumber b = new BencodeNumber();
        b.fromBencode(a.toBencode());

        assert a.equals(b);

        byte[] c = "i100.2e".getBytes();
        b = new BencodeNumber();
        b.fromBencode(c);

        assert Arrays.equals(c, b.toBencode());
    }

    @Test
    public void bytes(){
        BencodeBytes a = new BencodeBytes("Hello World");
        BencodeBytes b = new BencodeBytes();
        b.fromBencode(a.toBencode());

        assert a.equals(b);

        byte[] c = "11:HELLO WORLD".getBytes();
        b = new BencodeBytes();
        b.fromBencode(c);

        assert Arrays.equals(c, b.toBencode());
    }

    @Test
    public void nestedObject(){
        BencodeObject a = new BencodeObject();
        a.put("a", "HELLO WORLD");
        a.put("b", 100.2);
        BencodeObject b = new BencodeObject();
        b.put("d", "NEST");
        b.put("e", 66);
        a.put("c", b);

        BencodeObject c = new BencodeObject();
        c.fromBencode(a.toBencode());

        assert a.equals(c);

        byte[] d = "d1:a11:HELLO WORLD1:bi100.2e1:cd1:d4:NEST1:ei66eee".getBytes();
        c = new BencodeObject();
        c.fromBencode(d);

        assert Arrays.equals(d, c.toBencode());
    }

    @Test
    public void nestedArray(){
        BencodeArray a = new BencodeArray();
        a.add("HELLO WORLD");
        a.add(100.2);
        BencodeArray b = new BencodeArray();
        b.add("NEST");
        b.add(66);
        a.add(b);

        BencodeArray c = new BencodeArray();
        c.fromBencode(a.toBencode());

        assert a.equals(c);

        byte[] d = "l11:HELLO WORLDi100.2el4:NESTi66eee".getBytes();
        c = new BencodeArray();
        c.fromBencode(d);

        assert Arrays.equals(d, c.toBencode());
    }
}
