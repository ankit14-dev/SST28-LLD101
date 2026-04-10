package DistributedCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryDataStore<K, V> implements DataStore<K, V> {
    private final Map<K, V> db = new HashMap<>();

    @Override
    public V get(K key) {
        return db.get(Objects.requireNonNull(key, "key"));
    }

    @Override
    public void put(K key, V value) {
        db.put(Objects.requireNonNull(key, "key"), Objects.requireNonNull(value, "value"));
    }
}
