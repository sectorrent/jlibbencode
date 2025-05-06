package org.sectorrent.jlibbencode.utils;

public class ByteUtils {

    public static byte[] ensureCapacity(byte[] original, int requiredCapacity){
        if(original.length >= requiredCapacity){
            return original;
        }

        int newCapacity = Math.max(original.length*2, requiredCapacity);
        byte[] resized = new byte[newCapacity];
        System.arraycopy(original, 0, resized, 0, original.length);

        return resized;
    }
}
