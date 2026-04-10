package DistributedCache;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final LinkedHashSet<K> usageOrder = new LinkedHashSet<>();

    @Override
    public void onGet(K key) {
        touch(key);
    }

    @Override
    public void onPut(K key) {
        touch(key);
    }

    @Override
    public void onRemove(K key) {
        usageOrder.remove(Objects.requireNonNull(key, "key"));
    }

    @Override
    public K evictKey() {
        Iterator<K> iterator = usageOrder.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        K candidate = iterator.next();
        iterator.remove();
        return candidate;
    }

    private void touch(K key) {
        K nonNullKey = Objects.requireNonNull(key, "key");
        usageOrder.remove(nonNullKey);
        usageOrder.add(nonNullKey);
    }
}
