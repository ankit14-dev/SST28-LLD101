package DistributedCache;

import java.util.List;
import java.util.Objects;

public class ModuloDistributionStrategy<K, V> implements DistributionStrategy<K, V> {
    @Override
    public CacheNode<K, V> selectNode(K key, List<CacheNode<K, V>> nodes) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(nodes, "nodes");
        if (nodes.isEmpty()) {
            throw new IllegalStateException("No cache nodes configured");
        }
        int index = Math.floorMod(key.hashCode(), nodes.size());
        return nodes.get(index);
    }
}
