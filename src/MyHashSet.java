class MyHashSet {
   
   private int size;
   private int mod;
   private Node[] arr;
   private int INITIAL_CAPACITY = 7;
   
   private class Node {
      int val;
      Node next;
      
      public Node(int val, Node next) {
         this.val = val;
         this.next = next;
      }
      
      public Node(int val) {
         this(val, null);
      }
   }
   
   
   /** Initialize your data structure here. */
   public MyHashSet() {
      size = 0;
      mod = INITIAL_CAPACITY;
      arr = new Node[INITIAL_CAPACITY];
   }
   
   public void add(int key) {
      if (!contains(key)) {
         size++;
         if (size * 2 > mod) {
            expand();
         }
         insert(key, arr);
      }
   }
   
   private void insert(int key, Node[] nodes) {
      if (nodes[key % mod] == null) {
         nodes[key % mod] = new Node(key);
      } else {
         Node node = nodes[key % mod];
         while (node.next != null) {
            node = node.next;
         }
         node.next = new Node(key);
      }
   }
   
   private void expand() {
      Node[] newArr = new Node[arr.length * 2];
      mod = mod * 2;
      
      for (int i = 0; i < arr.length; i++) {
         Node curr = arr[i];
         while (curr != null) {
            int key = curr.val;
            insert(key, newArr);
            curr = curr.next;
         }
      }
      
      arr = newArr;
   }
   
   public void remove(int key) {
      if (contains(key)) {
         size--;
         Node node = arr[key % mod];
         if (node.val == key) {
            arr[key % mod] = node.next;
         } else {
            while (node.next.val != key) {
               node = node.next;
            }
            node.next = node.next.next;
         }
      }
   }
   
   /** Returns true if this set contains the specified element */
   public boolean contains(int key) {
      Node curr = arr[key % mod];
      while (curr != null) {
         if (curr.val == key) 
            return true;
         curr = curr.next;
      }
      return false;
   }
}