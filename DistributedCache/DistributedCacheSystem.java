package DistributedCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DistributedCacheSystem<K, V> implements Cache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy<K, V> distributionStrategy;
    private final DataStore<K, V> dataStore;

    public DistributedCacheSystem(List<CacheNode<K, V>> nodes, DistributionStrategy<K, V> distributionStrategy, DataStore<K, V> dataStore) {
        this.nodes = new ArrayList<>(Objects.requireNonNull(nodes, "nodes"));
        if (this.nodes.isEmpty()) {
            throw new IllegalArgumentException("At least one cache node is required");
        }
        this.distributionStrategy = Objects.requireNonNull(distributionStrategy, "distributionStrategy");
        this.dataStore = Objects.requireNonNull(dataStore, "dataStore");
    }

    @Override
    public V get(K key) {
        K nonNullKey = Objects.requireNonNull(key, "key");
        CacheNode<K, V> node = distributionStrategy.selectNode(nonNullKey, nodes);
        V value = node.get(nonNullKey);
        if (value != null) {
            return value;
        }

        V dbValue = dataStore.get(nonNullKey);
        if (dbValue != null) {
            node.put(nonNullKey, dbValue);
        }
        return dbValue;
    }

    @Override
    public void put(K key, V value) {
        K nonNullKey = Objects.requireNonNull(key, "key");
        V nonNullValue = Objects.requireNonNull(value, "value");

        // Assumption: write-through update keeps DB as source of truth.
        dataStore.put(nonNullKey, nonNullValue);

        CacheNode<K, V> node = distributionStrategy.selectNode(nonNullKey, nodes);
        node.put(nonNullKey, nonNullValue);
    }

    public List<CacheNode<K, V>> getNodes() {
        return List.copyOf(nodes);
    }
}
