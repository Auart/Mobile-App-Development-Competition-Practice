package edu.eschina.mall.utils;

import java.util.HashMap;
import java.util.Map;

public class HashMapProxy<K, V> extends HashMap<K, V> {





    public HashMapProxy(int initialCapacity) {
        super(initialCapacity);
    }


    public HashMapProxy() {
        super();
    }


    public HashMapProxy(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public HashMapProxy<K, V> putObject(K key, V value) {
        super.put(key, value);
        return this;
    }


}
