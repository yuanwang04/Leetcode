

class Test {
   public static void main(String[] args) {
      String str = "ab";
      String p = ".*c";
      Question q = new Question();
      int[] a = {1, 2, 3, 4, 5};
      int[] b = {2,5,6};
      int[] c = {0,0,0};
      int[] d = {8, 10};
      int[] e = {12, 16};
      int[][] t = {a};
      ListNode head = ListNode.fromArray(a);
      System.out.println(head);
      q.numTrees(3);
      System.out.println(q.simplifyPath("/.../"));
   }
}
