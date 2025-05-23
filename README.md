# jlibbencode

This is an implementation of Bencode for Java. Bencode is used for DHTs, Torrents, and Google DataServers. Its a lightweight fast data serialization.
[Wikipedia](https://en.wikipedia.org/wiki/Bencode)

I have also made an implementation of Bencode with [Rust](https://github.com/sectorrent/rlibbencode).

Library
-----
The JAR for the library can be found here: [Bencode JAR](https://github.com/secttorrent/jlibbencode)

Usage
-----
Here are some examples of how to use the Bencode library.

**Bencode Array**
```Java
//FROM LIST
ArrayList<String> l = new ArrayList<>();
BencodeArray bar = new BencodeArray(l);

//FROM BYTES
byte[] b; //ARRAY OF BYTES
BencodeArray bar = new BencodeArray(b);

//CREATE BENCODE
BencodeArray bar = new BencodeArray();
```

**Bencode Object | Map**
```Java
//FROM MAP
HashMap<String, String> l = new HashMap<>();
BencodeObject bob = new BencodeObject(l);

//FROM BYTES
byte[] b; //ARRAY OF BYTES
BencodeObject bob = new BencodeObject(b);

//CREATE BENCODE
BencodeObject bob = new BencodeObject();
```

**Put | Get data**
```Java
//ARRAY
bar.put(1000);
bar.get(0);

//MAP
bob.put("KEY", 100);
bob.get("KEY");
```

**Encoding to byte array**
```Java
bar.encode();
```

**Readable String**
```Java
System.out.println(bar.toString());
```
