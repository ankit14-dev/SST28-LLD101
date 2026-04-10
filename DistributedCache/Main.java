package DistributedCache;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataStore<String, String> database = new InMemoryDataStore<>();
        database.put("u:1", "Alice");
        database.put("u:2", "Bob");
        database.put("u:3", "Charlie");

        List<CacheNode<String, String>> nodes = Arrays.asList(
            new CacheNode<>("node-1", 2, new LRUEvictionPolicy<>()),
            new CacheNode<>("node-2", 2, new LRUEvictionPolicy<>()),
            new CacheNode<>("node-3", 2, new LRUEvictionPolicy<>())
        );

        Cache<String, String> cache = new DistributedCacheSystem<>(
            nodes,
            new ModuloDistributionStrategy<>(),
            database
        );

        System.out.println("First get u:1 (cache miss -> DB): " + cache.get("u:1"));
        System.out.println("Second get u:1 (cache hit): " + cache.get("u:1"));

        cache.put("u:4", "Daisy");
        System.out.println("Get u:4 after put (cache hit): " + cache.get("u:4"));

        cache.put("u:5", "Eve");
        cache.put("u:6", "Frank");
        cache.put("u:7", "Gina");

        System.out.println("Get u:2 (may be miss/hit depending on node LRU state): " + cache.get("u:2"));

        DistributedCacheSystem<String, String> system = (DistributedCacheSystem<String, String>) cache;
        for (CacheNode<String, String> node : system.getNodes()) {
            System.out.println(node.getNodeId() + " size=" + node.size());
        }
    }
}
