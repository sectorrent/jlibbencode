package org.sectorrent.jlibbencode.variables.inter;

public abstract class BencodeVariable {

    public abstract BencodeType getType();

    public abstract Object getObject();

    public abstract byte[] toBencode();
/*
    public void fromBencode(byte[] buf){
        fromBencode(buf);
    }
*/
    public abstract int fromBencode(byte[] buf);
}
