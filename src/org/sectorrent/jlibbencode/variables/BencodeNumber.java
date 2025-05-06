package org.sectorrent.jlibbencode.variables;

import org.sectorrent.jlibbencode.variables.inter.BencodeType;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;

public class BencodeNumber extends BencodeVariable {

    private byte[] b;

    public BencodeNumber(){
    }

    public BencodeNumber(Number n){
        b = n.toString().getBytes();
    }

    @Override
    public BencodeType getType(){
        return BencodeType.NUMBER;
    }

    @Override
    public Number getObject(){
        try{
            return NumberFormat.getInstance().parse(new String(b));
        }catch(ParseException e){
            throw new IllegalStateException("Object was not initialized");
        }
    }

    @Override
    public byte[] toBencode(){
        byte[] r = new byte[b.length+2];
        r[0] = (byte) BencodeType.NUMBER.getPrefix();
        r[r.length-1] = (byte) BencodeType.NUMBER.getSuffix();
        System.arraycopy(b, 0, r, 1, b.length);

        return r;
    }

    @Override
    public int fromBencode(byte[] buf, int off){
        if(!BencodeType.fromCode(buf[off]).equals(BencodeType.NUMBER)){
            throw new IllegalArgumentException("Byte array is not a bencode number.");
        }

        int s = 0;
        while(buf[off+s+1] != BencodeType.NUMBER.getSuffix()){
            s++;
        }

        b = new byte[s];
        System.arraycopy(buf, off+1, b, 0, b.length);

        return s+2;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof BencodeNumber){
            return Arrays.equals(toBencode(), ((BencodeNumber) o).toBencode());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(b);
    }

    @Override
    public String toString(){
        return new String(b);
    }
}
