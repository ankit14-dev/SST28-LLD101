package DistributedCache;

public interface DataStore<K, V> {
    V get(K key);

    void put(K key, V value);
}
