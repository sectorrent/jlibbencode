package org.sectorrent.jlibbencode.io;

import org.sectorrent.jlibbencode.variables.BencodeVariable;

import java.io.IOException;
import java.io.OutputStream;

public class BencodeWriter {

    private OutputStream out;

    public BencodeWriter(OutputStream out){
        this.out = out;
    }

    public void write(BencodeVariable ben)throws IOException {
        out.write(ben.toBencode());
    }

    public void flush()throws IOException {
        out.flush();
    }

    public void close()throws IOException {
        out.close();
    }
}
