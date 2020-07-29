public class ListNode {
   ListNode next;
   int val;
   
   public ListNode(int val) {
      this.val = val;
   }
   
   public ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
   }
   
   public static ListNode fromArray(int[] arr) {
      if (arr.length == 0) 
         return null;
      ListNode head = new ListNode(arr[0]);
      ListNode curr = head;
      for (int i = 1; i < arr.length; i++) {
         curr.next = new ListNode(arr[i]);
         curr = curr.next;
      }
      return head;
   }
   
   public String toString() {
      String result = "[";
      result += val;
      ListNode nextNode = next;
      while (nextNode != null) {
         result += ", " + nextNode.val;
         nextNode = nextNode.next;
      }
      result += "]";
      return result;
   }
}