package org.sectorrent.jlibbencode.variables.inter;

public enum BencodeType {

    NUMBER {
        public boolean isPrefix(char c){
            return c == getPrefix();
        }

        public byte getPrefix(){
            return 'i';
        }

        public byte getSuffix(){
            return 'e';
        }
    },
    OBJECT {
        public boolean isPrefix(char c){
            return c == getPrefix();
        }

        public byte getPrefix(){
            return 'd';
        }

        public byte getSuffix(){
            return 'e';
        }
    },
    ARRAY {
        public boolean isPrefix(char c){
            return c == getPrefix();
        }

        public byte getPrefix(){
            return 'l';
        }

        public byte getSuffix(){
            return 'e';
        }
    },
    BYTES {
        public boolean isPrefix(char c){
            return (c >= '0' && c <= '9');
        }

        public byte getDelimiter(){
            return ':';
        }
    };

    public byte getPrefix(){
        return 0x00;
    }

    public byte getSuffix(){
        return 0x00;
    }

    public byte getDelimiter(){
        return 0x00;
    }

    public static BencodeType fromCode(byte c){
        switch(c){
            case 'l':
                return BencodeType.ARRAY;
            case 'd':
                return BencodeType.OBJECT;
            case 'i':
                return BencodeType.NUMBER;
            default:
                if(c >= '0' && c <= '9'){
                    return BencodeType.BYTES;
                }else{
                    throw new IllegalArgumentException("Unknown Bencode prefix: "+(char) c);
                }
        }
    }
}
