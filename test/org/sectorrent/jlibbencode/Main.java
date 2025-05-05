package org.sectorrent.jlibbencode;

import org.sectorrent.jlibbencode.variables.BencodeArray;
import org.sectorrent.jlibbencode.variables.BencodeBytes;
import org.sectorrent.jlibbencode.variables.BencodeNumber;
import org.sectorrent.jlibbencode.variables.BencodeObject;

import java.io.IOException;

public class Main {

    public static void main(String[] args)throws IOException {
        BencodeNumber n = new BencodeNumber(100.3);
        System.out.println(new String(n.toBencode()));



        BencodeBytes s = new BencodeBytes("asdawdawd");
        System.out.println(new String(s.toBencode()));
        /*
        BencodeObject o = new BencodeObject();
        o.put("b", "bar");
        o.put("c", "far");
        o.put("n", 100);
        o.put("y",  new byte[]{ 0, 0, 0, 0, 0, 0 });

        o.put("array", new BencodeArray());
        o.getBencodeArray("array").add("n");
        o.getBencodeArray("array").add(123.56);

        o.put("object", new BencodeObject());
        o.getBencodeObject("object").put("z", "another one");
        o.getBencodeObject("object").put("n", "mutate");

        byte[] buf = o.encode();
        System.out.println("EXPECTED: "+o.byteSize()+" ACTUAL: "+buf.length);
        System.out.println(new String(buf));
        System.out.println(o);

        o = new BencodeObject(buf);
        System.out.println(o);
        */
    }
}
