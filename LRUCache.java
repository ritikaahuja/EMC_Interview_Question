
/**
 * Created by Ritika Ahuja on 3/16/2016.
 */
/* Implemetation of LRU Cache Data Structure using HashMap and a Doubly Linked List*/

import java.util.HashMap;

public class LRUCache {

    HashMap<Integer, Node> cache = new HashMap<Integer, Node>();
    Node head, tail;
    int size;
    int count;

    // To Intialize cache, linked list nodes and instance variables
    public LRUCache(int size) {
        this.size = size;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.previous = head;
        head.previous = null;
        tail.next = null;
        size = 0;
        count = 0;
    }

    // addValue()- adds the node to the beginning of the Linked List
    public void addValue(Node node) {
        node.next = head.next;
        node.next.previous = node;
        node.previous = head;
        head.next = node;
    }

    // deleteValue() - delete value is used to delete the node from the Linked list
    public void deleteValue(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    // getCachedValue()- takes the key as input and returns the value from the cache associated to that key
    public int getCachedValue(int key) {
        if (cache.get(key) != null) {
            Node node = cache.get(key);
            int res = node.data;
            deleteValue(node);
            addValue(node);
            return res;
        }
        return -1;
    }

    /*setCacheValue()- sets the value in the cache for a specific key
                   If the key is not present in the cache, it inserts a new key, value pair in the cache
     */
    public void setCacheValue(int key, int data) {
            if (cache.get(key) != null) {
                Node node = cache.get(key);
                node.data = data;
                deleteValue(node);
                addValue(node);
            } else {
                Node node = new Node(key, data);
                cache.put(key, node);
                if (count < size) {
                    count++;
                    addValue(node);
                } else {
                    cache.remove(tail.previous.key);
                    deleteValue(tail.previous);
                    addValue(node);
                }
            }
    }

    public static void main(String args[])
    {
        LRUCache obj = new LRUCache(20);
        Node first_node = new Node(10,20);

        obj.addValue(first_node);
        obj.setCacheValue(10,20);
        obj.getCachedValue(10);
    }
}

// Linked List Node Definition

class Node {
    int key;
    int data;
    Node previous;
    Node next;

    public Node(int key, int data) {
        this.key = key;
        this.data = data;
    }
}

