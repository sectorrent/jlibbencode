package org.sectorrent.jlibbencode.utils;

import org.sectorrent.jlibbencode.variables.BencodeArray;
import org.sectorrent.jlibbencode.variables.BencodeBytes;
import org.sectorrent.jlibbencode.variables.BencodeNumber;
import org.sectorrent.jlibbencode.variables.BencodeObject;
import org.sectorrent.jlibbencode.variables.inter.BencodeType;
import org.sectorrent.jlibbencode.variables.inter.BencodeVariable;

public class BencodeUtils {

    public static BencodeVariable unpackBencode(byte[] buf, int off){
        BencodeType type = BencodeType.getTypeByPrefix((char) buf[off]);

        BencodeVariable variable;
        switch(type){
            case NUMBER:
                variable = new BencodeNumber();
                break;

            case ARRAY:
                variable = new BencodeArray();
                break;

            case OBJECT:
                variable = new BencodeObject();
                break;

            case BYTES:
                variable = new BencodeBytes();
                break;

            default:
                throw new IllegalArgumentException("Invalid key type.");
        }

        variable.fromBencode(buf, off);
        return variable;
    }
}
