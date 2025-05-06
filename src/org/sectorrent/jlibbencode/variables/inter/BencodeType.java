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
    }, INVALID;

    public boolean isPrefix(char c){
        return false;
    }

    public byte getPrefix(){
        return 0x00;
    }

    public byte getSuffix(){
        return 0x00;
    }

    public byte getDelimiter(){
        return 0x00;
    }

    public static BencodeType getTypeByPrefix(char c){
        for(BencodeType type : values()){
            if(type.isPrefix(c)){
                return type;
            }
        }

        return INVALID;
    }
}
