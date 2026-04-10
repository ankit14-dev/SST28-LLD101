package DistributedCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CacheNode<K, V> {
    private final String nodeId;
    private final int capacity;
    private final EvictionPolicy<K> evictionPolicy;
    private final Map<K, V> entries;

    public CacheNode(String nodeId, int capacity, EvictionPolicy<K> evictionPolicy) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        this.nodeId = Objects.requireNonNull(nodeId, "nodeId");
        this.capacity = capacity;
        this.evictionPolicy = Objects.requireNonNull(evictionPolicy, "evictionPolicy");
        this.entries = new HashMap<>();
    }

    public String getNodeId() {
        return nodeId;
    }

    public V get(K key) {
        K nonNullKey = Objects.requireNonNull(key, "key");
        V value = entries.get(nonNullKey);
        if (value != null) {
            evictionPolicy.onGet(nonNullKey);
        }
        return value;
    }

    public void put(K key, V value) {
        K nonNullKey = Objects.requireNonNull(key, "key");
        V nonNullValue = Objects.requireNonNull(value, "value");

        if (!entries.containsKey(nonNullKey) && entries.size() >= capacity) {
            K keyToEvict = evictionPolicy.evictKey();
            if (keyToEvict != null) {
                entries.remove(keyToEvict);
            }
        }

        entries.put(nonNullKey, nonNullValue);
        evictionPolicy.onPut(nonNullKey);
    }

    public int size() {
        return entries.size();
    }

    public boolean contains(K key) {
        return entries.containsKey(key);
    }
}
