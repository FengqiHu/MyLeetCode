import java.util.*;
import java.util.LinkedList;

/**
 * @author louishu
 * @date 3/6/26 14:30
 * @description
 */
public class LRUCache {
    /**
     * Q146 LRU
     *
     * @Date - 03/06/2026
     */
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

    class LRU {
        private class Node {
            int key, value;
            Node prev, next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> cache;
        private final Node head, tail;

        public LRU(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            // point to each other
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            remove(node);
            insertToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                remove(node);
                insertToHead(node);
                return;
            }

            if (cache.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                cache.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            insertToHead(newNode);
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }
}
