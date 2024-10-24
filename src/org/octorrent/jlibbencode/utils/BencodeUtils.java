package org.octorrent.jlibbencode.utils;

import org.octorrent.jlibbencode.variables.BencodeArray;
import org.octorrent.jlibbencode.variables.BencodeBytes;
import org.octorrent.jlibbencode.variables.BencodeNumber;
import org.octorrent.jlibbencode.variables.BencodeObject;
import org.octorrent.jlibbencode.variables.inter.BencodeType;
import org.octorrent.jlibbencode.variables.inter.BencodeVariable;

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

        variable.decode(buf, off);
        return variable;
    }
}
