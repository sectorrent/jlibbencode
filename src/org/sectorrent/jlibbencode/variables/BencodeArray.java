package org.sectorrent.jlibbencode.variables;

import org.sectorrent.jlibbencode.variables.inter.BencodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.sectorrent.jlibbencode.utils.ByteUtils.ensureCapacity;

public class BencodeArray extends BencodeVariable {

    private List<BencodeVariable> l;

    public BencodeArray(){
        l = new ArrayList<>();
    }

    public BencodeArray(List<?> l){
        for(Object v : l){
            if(v instanceof BencodeVariable){
                add((BencodeVariable) v);
            }else if(v instanceof Number){
                add((Number) v);
            }else if(v instanceof String){
                add((String) v);
            }else if(v instanceof byte[]){
                add((byte[]) v);
            }else if(v instanceof List<?>){
                add((List<?>) v);
            }else if(v instanceof Map<?, ?>){
                add((Map<?, ?>) v);
            }
        }
    }

    public BencodeArray(byte[] buf){
        this();
        fromBencode(buf);
    }

    private void add(BencodeVariable v){
        l.add(v);
    }

    public void add(Number n){
        add(new BencodeNumber(n));
    }

    public void add(byte[] b){
        add(new BencodeBytes(b));
    }

    public void add(String s){
        add(new BencodeBytes(s.getBytes()));
    }

    public void add(List<?> l){
        add(new BencodeArray(l));
    }

    public void add(Map<?, ?> l){
        add(new BencodeObject(l));
    }

    public void add(BencodeArray a){
        l.add(a);
    }

    public void add(BencodeObject o){
        l.add(o);
    }

    private void set(int i, BencodeVariable v){
        l.set(i, v);
    }

    public void set(int i, Number n){
        set(i, new BencodeNumber(n));
    }

    public void set(int i, byte[] b){
        set(i, new BencodeBytes(b));
    }

    public void set(int i, String s){
        set(i, new BencodeBytes(s.getBytes()));
    }

    public void set(int i, List<?> l){
        set(i, new BencodeArray(l));
    }

    public void set(int i, Map<?, ?> m){
        set(i, new BencodeObject(m));
    }

    public void set(int i, BencodeArray a){
        l.set(i, a);
    }

    public void set(int i, BencodeObject o){
        l.set(i, o);
    }

    public BencodeVariable valueOf(int i){
        return l.get(i);
    }

    public Object get(int i){
        return l.get(i).getObject();
    }

    public Integer getInteger(int i){
        return ((Number) l.get(i).getObject()).intValue();
    }

    public Long getLong(int i){
        return ((Number) l.get(i).getObject()).longValue();
    }

    public Short getShort(int i){
        return ((Number) l.get(i).getObject()).shortValue();
    }

    public Double getDouble(int i){
        return ((Number) l.get(i).getObject()).doubleValue();
    }

    public Float getFloat(int i){
        return ((Number) l.get(i).getObject()).floatValue();
    }

    public String getString(int i){
        return new String((byte[]) l.get(i).getObject());
    }

    public byte[] getBytes(int i){
        return (byte[]) l.get(i).getObject();
    }

    public BencodeArray getBencodeArray(int i){
        return (BencodeArray) l.get(i);
    }

    public BencodeObject getBencodeObject(int i){
        return (BencodeObject) l.get(i);
    }

    public boolean contains(Number n){
        return l.contains(new BencodeNumber(n));
    }

    public boolean contains(String s){
        return l.contains(new BencodeBytes(s.getBytes()));
    }

    public boolean contains(byte[] b){
        return l.contains(new BencodeBytes(b));
    }

    public boolean contains(List<?> l){
        return this.l.contains(new BencodeArray(l));
    }

    public boolean contains(Map<?, ?> m){
        return l.contains(new BencodeObject(m));
    }

    public boolean contains(BencodeArray a){
        return l.contains(a);
    }

    public boolean contains(BencodeObject o){
        return l.contains(o);
    }

    private void remove(BencodeVariable v){
        if(l.contains(v)){
            l.remove(v);
        }
    }

    public void remove(Number n){
        remove(new BencodeNumber(n));
    }

    public void remove(byte[] b){
        remove(new BencodeBytes(b));
    }

    public void remove(String s){
        remove(new BencodeBytes(s.getBytes()));
    }

    private int indexOf(BencodeVariable v){
        if(l.contains(v)){
            return l.indexOf(v);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int indexOf(Number n){
        return indexOf(new BencodeNumber(n));
    }

    public int indexOf(byte[] b){
        return indexOf(new BencodeBytes(b));
    }

    public int indexOf(String s){
        return indexOf(new BencodeBytes(s.getBytes()));
    }

    public int size(){
        return l.size();
    }

    @Override
    public BencodeType getType(){
        return BencodeType.ARRAY;
    }

    @Override
    public Object getObject(){
        List<Object> a = new ArrayList<>();
        for(BencodeVariable v : l){
            a.add(v.getObject());
        }
        return a;
    }

    @Override
    public byte[] toBencode(){
        byte[] r = new byte[128];
        r[0] = BencodeType.ARRAY.getPrefix();

        int off = 1;
        for(BencodeVariable v : l){
            byte[] value = v.toBencode();
            r = ensureCapacity(r, off+value.length);
            System.arraycopy(value, 0, r, off, value.length);
            off += value.length;
        }

        r = ensureCapacity(r, off);
        r[off] = BencodeType.ARRAY.getSuffix();
        off++;

        byte[] result = new byte[off];
        System.arraycopy(r, 0, result, 0, off);
        return result;
    }

    @Override
    public int fromBencode(byte[] buf, int off){
        if(!BencodeType.fromCode(buf[off]).equals(BencodeType.ARRAY)){
            throw new IllegalArgumentException("Byte array is not a bencode array.");
        }

        int s = 1;
        while(buf[off+s] != BencodeType.ARRAY.getSuffix()){
            BencodeVariable value;
            switch(BencodeType.fromCode(buf[off+s])){
                case NUMBER:
                    value = new BencodeNumber();
                    break;

                case ARRAY:
                    value = new BencodeArray();
                    break;

                case OBJECT:
                    value = new BencodeObject();
                    break;

                case BYTES:
                    value = new BencodeBytes();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid key type.");
            }

            s += value.fromBencode(buf, off+s);
            l.add(value);
        }

        return s+1;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof BencodeArray){
            return l.equals(((BencodeArray) o).l);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return l.hashCode();
    }

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder("[\r\n");

        for(BencodeVariable v : l){
            switch(v.getType()){
                case NUMBER:
                    b.append("\t\033[0;33m"+v+"\033[0m\r\n");
                    break;

                case ARRAY:
                case OBJECT:
                    b.append("\t\033[0m"+v.toString().replaceAll("\\r?\\n", "\r\n\t")+"\r\n");
                    break;

                case BYTES:
                    b.append("\t\033[0;34m"+v+"\033[0m\r\n");
                    break;
            }
        }

        return b+"]";
    }
}
