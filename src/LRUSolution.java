import java.util.*;


public class LRUSolution {

    public static void main(String[] args) {
        LRUSolution sol = new LRUSolution();
        int[][] operations = new int[6][];
        operations[0] = new int[]{1,1,1};
        operations[1] = new int[]{1,1,2};
        operations[2] = new int[]{1,1,2};
        operations[3] = new int[]{2,1};
        operations[4] = new int[]{1,1,4};
        operations[5] = new int[]{2,1};
        System.out.println(Arrays.toString(sol.LRU(operations, 1)));
    }
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        LRU lru = new LRU(k);
        List<Integer> result = new ArrayList<>();
        for (int[] operation: operators) {
            if (operation[0] == 1) {
                int key = operation[1];
                int val = operation[2];
                lru.set(key, val);
            } else if (operation[0] == 2) {
                int ans = lru.get(operation[1]);
                result.add(ans);
            }
        }
        int[] formatted = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            formatted[i] = result.get(i);
        }
        return formatted;
    }
    
    public static class LRU {
        public static class Node {
            Node prev;
            Node next;
            int key;
            int val;
            
            public Node(int k, int v) {
                key = k;
                val = v;
            }
        }
        
        Node head;
        Node tail;
        Map<Integer, Node> keyToNode;
        int size;
        
        public LRU(int size) {
            this.size = size;
            head = null;
            tail = null;
            keyToNode = new HashMap<>();
        }
        
        public int get(int key) {
            if (keyToNode.containsKey(key)) {
                moveToFront(keyToNode.get(key));
                return keyToNode.get(key).val;
            }
            return -1;
        }
        
        public void set(int key, int val) {
            if (keyToNode.containsKey(key)) {
                moveToFront(keyToNode.get(key));
                keyToNode.get(key).val = val;
            } else if (keyToNode.size() < size) { 
                Node n = new Node(key, val);
                keyToNode.put(key, n);
                addToFront(n);
            } else {
                keyToNode.remove(tail.key);
                removeTail();
                Node n = new Node(key, val);
                keyToNode.put(key, n);
                addToFront(n);
            }
        }
        
        public void moveToFront(Node node) {
            if (head == node) {
                return;
            }
            node.prev.next = node.next;
            if (tail == node) {
                tail = node.prev;
            } else {
                node.next.prev = node.prev;
            }
            node.next = head;
            head.prev = node;
            node.prev = null;
            head = node;
        }
        
        public void addToFront(Node node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                node.prev = null;
                head = node;
            }
        }
        
        public void removeTail() {
            if (tail == head) {
                tail = null;
                head = null;
            } else {
                tail.prev.next = null;
                tail = tail.prev;
            }
        }
    }
}