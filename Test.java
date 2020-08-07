import java.util.*;

class Test {
   public static void main(String[] args) {
      String str = "ab";
      String p = ".*c";
      Question q = new Question();
      int[] a = {1, 2};
      int[] b = {2,5,6};
      int[] c = {0,0,0};
      int[] d = {8, 10};
      int[] e = {12, 16};
      int[][] t = {a};
      String[] strs= {"hot","dot","dog","lot","log","cog"};
         
      Solution128 sl = new Solution128();
      System.out.println(q.roomTransport(2, a));
   }
}
