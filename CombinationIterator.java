/*
A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
A function next() that returns the next combination of length combinationLength in lexicographical order.
A function hasNext() that returns True if and only if there exists a next combination.
*/

class CombinationIterator {
   
   private String sb;
   private int[] pointers;
   private int maxL;

   public CombinationIterator(String characters, int combinationLength) {
      sb = characters;
      pointers = new int[combinationLength];
      maxL = sb.length();
      for (int i = 0; i < pointers.length; i++) {
         pointers[i] = i;
      }
   }
   
   public String next() {
      StringBuffer res = new StringBuffer();
      for (int i = 0; i < pointers.length; i++) {
         res.append(sb.charAt(pointers[i]));
      }
      
      increment(pointers.length - 1);
      
      return res.toString();
   }
   
   private void increment(int index) {
      pointers[index] ++;
      if (index != 0) {
         if ((index == pointers.length - 1 && pointers[index] == maxL) ||
             (index < pointers.length - 1 && pointers[index] == pointers[index + 1])) {
            increment(index - 1);
            pointers[index] = pointers[index - 1] + 1;
         }
      }
   }
   
   public boolean hasNext() {
      return pointers[pointers.length - 1] != maxL;
   }
   
   public static void main(String[] args) {
   
      CombinationIterator ci = new CombinationIterator("aabc", 3);
      while (ci.hasNext()) {
         System.out.println(ci.next());
      }
   
   }
}