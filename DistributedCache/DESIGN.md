# Distributed Cache Design

## Class Diagram

```mermaid
classDiagram
    class Cache~K,V~ {
      <<interface>>
      +get(key) V
      +put(key, value)
    }

    class DistributedCacheSystem~K,V~ {
      -List~CacheNode~ nodes
      -DistributionStrategy distributionStrategy
      -DataStore dataStore
      +get(key) V
      +put(key, value)
    }

    class CacheNode~K,V~ {
      -String nodeId
      -int capacity
      -Map~K,V~ entries
      -EvictionPolicy evictionPolicy
      +get(key) V
      +put(key, value)
    }

    class DistributionStrategy~K,V~ {
      <<interface>>
      +selectNode(key, nodes) CacheNode
    }

    class ModuloDistributionStrategy~K,V~ {
      +selectNode(key, nodes) CacheNode
    }

    class EvictionPolicy~K~ {
      <<interface>>
      +onGet(key)
      +onPut(key)
      +onRemove(key)
      +evictKey() K
    }

    class LRUEvictionPolicy~K~ {
      -LinkedHashSet usageOrder
      +evictKey() K
    }

    class DataStore~K,V~ {
      <<interface>>
      +get(key) V
      +put(key, value)
    }

    class InMemoryDataStore~K,V~ {
      -Map~K,V~ db
      +get(key) V
      +put(key, value)
    }

    Cache <|.. DistributedCacheSystem
    DistributedCacheSystem --> CacheNode
    DistributedCacheSystem --> DistributionStrategy
    DistributedCacheSystem --> DataStore

    DistributionStrategy <|.. ModuloDistributionStrategy
    CacheNode --> EvictionPolicy
    EvictionPolicy <|.. LRUEvictionPolicy
    DataStore <|.. InMemoryDataStore
```

## Design Explanation

- Data distribution across nodes:
  - `DistributedCacheSystem` delegates node selection to `DistributionStrategy`.
  - Current implementation uses `ModuloDistributionStrategy`: `index = floorMod(hash(key), nodeCount)`.
  - To support consistent hashing later, add `ConsistentHashDistributionStrategy` implementing `DistributionStrategy`.

- Cache miss flow (`get`):
  - Pick target node using distribution strategy.
  - If key exists in node, return directly (cache hit).
  - If absent, fetch from `DataStore`, write into selected node, return fetched value.

- Eviction behavior:
  - Every `CacheNode` has fixed capacity and pluggable `EvictionPolicy`.
  - Current `LRUEvictionPolicy` tracks recency using ordered set semantics.
  - On insert when full, node asks policy for `evictKey()` and removes that key.

- Write behavior (`put`):
  - Assumption used: **write-through**.
  - `put(key, value)` updates `DataStore` first, then updates cache node.

- Extensibility:
  - Distribution is strategy-based (`DistributionStrategy`).
  - Eviction is strategy-based (`EvictionPolicy`).
  - Storage backend is abstracted (`DataStore`).
  - Node internals are isolated in `CacheNode`.
