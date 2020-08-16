import java.util.*;

class TrieNode {
   int size;
   boolean used;
   TrieNode one;
   TrieNode zero;

   public TrieNode(boolean used) {
      this.used = used;
   }
}


public class NXorGreaterM {
    
   static final int MAX_POW = 17;
    
   public static void main(String[] args) {
      TrieNode root = new TrieNode(false);
      Scanner in = new Scanner(System.in);
      int n = in.nextInt(); 
      int m = in.nextInt();
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
         nums[i] = in.nextInt();
         addNode(root, nums[i]);
      }
      int res = 0;
      for (int i = 0; i < n; i++) {
         res += getResultOfN(nums[i], m, root, 1 << MAX_POW);
      }
      System.out.println(res / 2);
   }
    
   public static int getResultOfN(int n, int m, TrieNode root, int mul) {
      if (root == null) 
         return 0;
      if ((n & mul) == 0 && (m & mul) == 0) {
         return (root.one == null ? 0 : root.one.size) +
                getResultOfN(n, m, root.zero, mul >> 1);
      } else if ((n & mul) != 0 && (m & mul) == 0) {
         return (root.zero == null ? 0 : root.zero.size) +
                getResultOfN(n, m, root.one, mul >> 1);
      } else if ((n & mul) == 0 && (m & mul) != 0) {
         return getResultOfN(n, m, root.one, mul >> 1);
      } else { // n & mul == 1 && m & mul == 1
         return getResultOfN(n, m, root.zero, mul >> 1);
      }
   }
    
   public static void addNode(TrieNode root, int val) {
      int n = 1 << MAX_POW;
      while (n > 0) {
         if (val >= n) {
            val -= n;
            if (root.one == null) {
               root.one = new TrieNode(false);
            }
            root.size++;
            root = root.one;
         } else {
            if (root.zero == null) {
               root.zero = new TrieNode(false);
            }
            root.size++;
            root = root.zero;
         }
         n = n >> 1;
      }
      root.size++;
      root.used = true;
   }

}