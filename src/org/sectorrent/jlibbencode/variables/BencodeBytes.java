package org.sectorrent.jlibbencode.variables;

import org.sectorrent.jlibbencode.variables.inter.BencodeType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class BencodeBytes extends BencodeVariable {

    private byte[] b;

    public BencodeBytes(){
    }

    public BencodeBytes(byte[] b){
        this.b = b;
    }

    public BencodeBytes(String s){
        this.b = s.getBytes();
    }

    @Override
    public BencodeType getType(){
        return BencodeType.BYTES;
    }

    @Override
    public byte[] getObject(){
        return b;
    }

    @Override
    public byte[] toBencode(){
        int t = b.length, d = 0;
        while(t != 0){
            t /= 10;
            d++;
        }

        byte[] r = new byte[b.length+d+1];

        t = b.length;
        for(int i = d-1; i >= 0; i--){
            r[i] = (byte) ((t%10)+'0');
            t /= 10;
        }

        r[d] = (byte) BencodeType.BYTES.getDelimiter();
        System.arraycopy(b, 0, r, d+1, b.length);

        return r;
    }

    @Override
    public int fromBencode(byte[] buf, int off){
        //if(!BencodeType.getTypeByPrefix((char) buf[off]).equals(BencodeType.BYTES)){
        //    throw new IllegalArgumentException("Byte array is not a bencode bytes / string.");
        //}

        int s = 0;
        while(buf[off+s] != BencodeType.BYTES.getDelimiter()){
            s++;
        }

        int length = 0;
        for(int i = 0; i < s; i++){
            length = length*10+(buf[off+i]-'0');
        }

        b = new byte[length];
        System.arraycopy(buf, off+s+1, b, 0, b.length);

        return s+length+1;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof BencodeBytes){
            return Arrays.equals(b, ((BencodeBytes) o).b);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(b);
    }

    @Override
    public String toString(){
        if(Charset.forName("US-ASCII").newEncoder().canEncode(new String(b))){
            return "\""+new String(b, StandardCharsets.UTF_8)+"\"";
        }

        return Base64.getEncoder().encodeToString(b);
    }
}
