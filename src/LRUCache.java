import java.util.*;
import java.util.LinkedList;

/**
 * @author louishu
 * @date 3/6/26 14:30
 * @description
 */
public class LRUCache {
    int capacity;
    Map<Integer, Integer> cache = new HashMap<>();
    Queue<Integer> keyCache = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            // remove the old pos
            keyCache.remove(key);
            keyCache.add(key);
            // return the value
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        // remove the old pos
        if (cache.containsKey(key))
            keyCache.remove(key);
        // exceed capacity, and new key

        else if(this.cache.size() == capacity) {
            // delete key in two caches
            int removeKey = keyCache.poll();;
            cache.remove(removeKey);
        }

        keyCache.add(key);
        //same key, do not update keycache
        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // cache is {1=1}
        lRUCache.put(3, 2); // cache is {1=1, 2=2}
        lRUCache.get(3);    // return -1 (not found)
        lRUCache.get(2);    // return 3
        lRUCache.put(4, 1); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}

    }
}
