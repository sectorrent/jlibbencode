package org.sectorrent.jlibbencode.variables;

import org.sectorrent.jlibbencode.variables.inter.BencodeType;

public abstract class BencodeVariable {

    public abstract BencodeType getType();

    public abstract Object getObject();

    public abstract byte[] toBencode();

    public void fromBencode(byte[] buf){
        fromBencode(buf, 0);
    }

    protected abstract int fromBencode(byte[] buf, int off);
}
