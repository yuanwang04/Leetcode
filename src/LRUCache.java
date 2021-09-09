/*

    A cache system using doubly-linked-list and hashmap
    to realize O(1) put and get of cache. When capacity
    is reached, Least-Recently-Used key-value pair is 
    replaced.

*/

import java.util.*;

public class LRUCache {

   private int capacity;
   private Map<Integer, Node> map;
   private Node head;
   private Node tail;
   
   public static void main(String[] args) {
   
      LRUCache cache = new LRUCache(2);
      cache.put(2, 1);
      cache.get(2);
   
   
   }
   
   public LRUCache(int capacity) {
      map = new HashMap<>();
      this.capacity = capacity;
   }
   
   public int get(int key) {
      if (!map.containsKey(key)) {
         return -1;
      } else {
         // update
         Node node = map.get(key);
         remove(node);
         addToHead(node);
         return node.val;
      }
   }
   
   public void put(int key, int value) {
      if (map.containsKey(key)) {
         
         // update, remove original
         Node node = map.get(key);
         remove(node);
      } else if (map.size() == capacity) {
         // remove LRU
         map.remove(tail.key);
         remove(tail);
      }
      // add new to head
      Node node = new Node(key, value);
      addToHead(node);
      map.put(key, node);
   }
   
   private void remove(Node node) {
      if (node == head || node == tail) {
         if (node == head) {
            head = head.next;
            if (head != null) {
               head.prev = null;
            }
         }
         if (node == tail) {
            tail = tail.prev;
            if (tail != null) {
               tail.next = null;
            }
         }
      } else {
         Node pre = node.prev;
         Node nex = node.next;
         pre.next = nex;
         nex.prev = pre;
      }
      node.prev = null;
      node.next = null;
      
   }
   
   private void addToHead(Node node) {
      if (head == null) {
         head = node;
         tail = node;
      } else {
         node.next = head;
         head.prev = node;
         head = node;
      }
   }
   
   class Node {
      int key;
      int val;
      Node next;
      Node prev;
      
      public Node(int key, int val) {
         this.key = key;
         this.val = val;
      }
   }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */