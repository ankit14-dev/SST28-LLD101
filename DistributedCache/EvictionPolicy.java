package DistributedCache;

public interface EvictionPolicy<K> {
    void onGet(K key);

    void onPut(K key);

    void onRemove(K key);

    K evictKey();
}
