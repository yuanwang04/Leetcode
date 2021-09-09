import java.util.*;
import java.io.*;

public class Question implements Solution { 
   
   public static int myAtoi(String str) {
      while (str.length() > 0) { // get rid of leading white space
         if (str.charAt(0) != ' ') {
            break;
         } else {
            str = str.substring(1);
         }
      }
      boolean neg = false;
      if (str.length() == 0) {
         return 0;}
      if (!Character.isDigit(str.charAt(0))) { // check the first after white space
         if (str.charAt(0) == '-') {
            neg = true;
            str = str.substring(1);
         } else if (str.charAt(0) == '+') {
            str = str.substring(1);
         } else {
            return 0;
         }
      }
      
      String result = ""; // build up a parsing string
      for (int i = 0; i < str.length(); i++) {
         if (Character.isDigit(str.charAt(i))) {
            result += str.charAt(i);
         } else {
            break;
         }
      }
      
      int num = 0;
      
      if (neg) {
         result = "-" + result;
      }
      
      if (result.length() == 0) {
         return num;
      }
      try {
         num = Integer.parseInt(result);
      } catch(Exception e) {
         if (neg) {
            return Integer.MIN_VALUE;
         } else {
            return Integer.MAX_VALUE;
         }
      }
      
      return num;
   }
   
   public static boolean isMatch(String s, String p) {
      if (s.equals(p)) {
         return true;
      }
      
      if (p.length() < 2) {
         if (p.equals("") && s.length() == 1) {
            return true;
         } else {
            return s.equals(p);
         }
      }
      
      // p.length() >= 2
      if (p.charAt(1) != '*') {
         if (s.length() == 0) {
            return false;
         }
         if (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0)) { // s.length() >= 1
            return isMatch(s.substring(1), p.substring(1));
         } else {
            return false;
         }
      }
      
      // p.charAt(0) == letter
      boolean match = isMatch(s, p.substring(2));
      while (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
       && !match) {
         s = s.substring(1);
         match = match || isMatch(s, p.substring(2));
      }
      
      return match;
      
   }
    
   private static List<List<Integer>> twoSum(int[] nums, int removed, Set<Set<Integer>> solutionSet) {
      
      int target = -nums[removed];
      Map<Integer, Integer> map = new HashMap<>();
      List<List<Integer>> result = new ArrayList<>();
        
      for (int i = 0; i < nums.length; i++) {
         if (i != removed) {
            if (map.containsKey(nums[i])) {
               Set<Integer> oneSet = new HashSet<>();
               oneSet.add(target - nums[i]);
               oneSet.add(nums[i]);
               oneSet.add(nums[removed]);
               if (checkUnique(solutionSet, oneSet)) {
                  solutionSet.add(oneSet);
                  List<Integer> oneSolution = new ArrayList<>();
                  oneSolution.add(target - nums[i]);
                  oneSolution.add(nums[i]);
                  oneSolution.add(nums[removed]);
                  result.add(oneSolution);
               }
            } else{
               map.put(target - nums[i], i);
            }
         }
      }
      return result;
   }
    
   private static boolean checkUnique(Set<Set<Integer>> allSolutions, Set<Integer> given) {
      for (Set<Integer> include : allSolutions) {
         if (include.equals(given)) {
            return false;
         }
      }
      return true;
   }

   
   public String minWindow(String s, String t) {
      Map<Character, Integer> target = new HashMap<>();
      Map<Character, Integer> current = new HashMap<>();
      int i = 0; 
      int j = 0;
      int minLength = s.length()+1;
      String result = "";
      int formed = 0;
        // construct target
      for (int k = 0; k < t.length(); k++) {
         target.put(t.charAt(k), target.getOrDefault(t.charAt(k), 0) + 1);
      }
        
      while (j < s.length()) {
            //expand
         char c = s.charAt(j);
         int count = current.getOrDefault(c, 0) + 1;
         current.put(c, count);
         j++;
         if (target.containsKey(c) && target.get(c) == count) {
            formed++;
         }
            //shrink
         while (formed == target.size() && i < j) {
            if (j-i < minLength) {
               minLength = j-i;
               result = s.substring(i, j);
            }
            char ic = s.charAt(i);
            int newCount = current.get(ic) - 1;
            current.put(ic, newCount);
            if (target.containsKey(ic) && target.get(ic) > newCount) {
               formed--;
            }
            i++;
         }
      }
      return result;
   }
   
   public int searchInsert(int[] nums, int target) {
      if (nums.length == 0) {
         return 0;
      }
      return binary(nums, target, 0, nums.length-1);
   }
    
   public int binary(int[] nums, int target, int i, int j) {
      if (i >= j) {
         if (nums[i] >= target) {
            return i;
         } else {
            return i+1;
         }
      }
      int mid = (i + j) / 2;
      if (target == nums[mid]) {
         return mid;
      }
      if (target > nums[mid]) {
         return binary(nums, target, mid+1, j);
      } else {
         return binary(nums, target, i, mid-1);
      }
   }
 

   public double myPow(double x, int n) {
      if (n == 0) {
         return 1;}
      if (n == 1) {
         return x;}
        
      double part = myPow(x, n/2);
      double result = part * part;
        
      if (n % 2 == 0) {
         return result;}
      if (n % 2 == 1) {
         return result * x;}
        
      return result / x;
   }
   
   public int[][] merge(int[][] intervals) {
      if (intervals.length <= 1) {
         return intervals;
      }
      Quicksort.quicksort(intervals, 0, intervals.length-1);
      int top = intervals[0][1];
      int bot = intervals[0][0];
      List<int[]> lst = new ArrayList<>();
      for (int i = 0; i < intervals.length; i++) {
         if (intervals[i][0] > top) {
            lst.add(new int[]{bot, top});
            top = intervals[i][1];
            bot = intervals[i][0];
         } else {
            top = Math.max(top, intervals[i][1]);
         }
      }
      lst.add(new int[]{bot, top});
      int[][] result = new int[lst.size()][2];
      for (int i = 0; i < lst.size(); i++) {
         result[i] = lst.get(i);
      }
      return result;
   }
   

   
   public int[][] insert(int[][] intervals, int[] newInterval) {
        // return new interval
      if (intervals.length == 0) {
         return new int[][]{newInterval};
      }
        //insert at front
      if (newInterval[1] < intervals[0][0]) {
         int[][] result = new int[intervals.length+1][2];
         result[0] = newInterval;
         for (int i = 1; i < result.length; i++) {
            result[i] = intervals[i-1];
         }
         return result;
      }
        // insert at back
      if (newInterval[0] > intervals[1][0]) {
         int[][] result = new int[intervals.length+1][2];
         result[intervals.length] = newInterval;
         for (int i = 0; i < result.length; i++) {
            result[i] = intervals[i];
         }
         return result;
      }
        
      int lo = newInterval[0];
      int hi = newInterval[1];
      int insert = 0;
      int merged = 0;
      int i = 0;
      while (i < intervals.length && lo > intervals[i][1]) {
         i++;
      }
      insert = i;
      lo = Math.min(lo, intervals[i][0]);
      while (i < intervals.length && hi > intervals[i][0]) {
         merged++;
         i++;
      }
      hi = Math.max(hi, intervals[i-1][1]);
      int[][] result = new int[intervals.length+1-merged][2];
      if (merged == 0) {
         for (i = 0; i < insert; i++) {
            result[i] = intervals[i];
         }
         result[insert] = newInterval;
         for (i = insert+1; i < result.length; i++) {
            result[i] = intervals[i-1];
         }
      } else {
         for (i = 0; i < insert; i++) {
            result[i] = intervals[i];
         }
         result[insert] = new int[]{lo, hi};
         for (i = insert+1; i < result.length; i++) {
            result[i] = intervals[i-1+merged];
         }
      }
      return result;
   }
   

   public String getPermutation(int n, int k) {
      List<Integer> lst = new ArrayList<>();
      String result = "";
      for (int i = 1; i <= n; i++) {
         lst.add(i);
      }
      
      int[] fact = fact(n);
      int count = n-1;
      k--;
      while (k > 0) {
         int temp = k/fact[count-1];
         result += lst.remove(temp);
         k = k % fact[count-1];
         count--;
      }
      while (!lst.isEmpty()) {
         result += lst.remove(0);
      }
      return result;
   }
    
   public int[] fact(int n) {
      int[] r = new int[n];
      r[0] = 1;
      for (int i = 1; i < n; i++) {
         r[i] = r[i-1] * (i+1);
      }
      return r;
   }
   
   public int uniquePaths(int m, int n) {
      if (m == 1 || n == 1) {
         return 1;
      }
      int[] fact = fact(m+n-2);
      return fact[m+n-3] / fact[m-2] / fact[n-2];
   }
   

   
   public String addBinary(String a, String b) {
      if (a.isEmpty()) 
         return b;
      if (b.isEmpty()) 
         return a;
      if (a.equals("0")) 
         return b;
      if (b.equals("0")) 
         return a;
        
      int al = a.length();
      int bl = b.length();
      int rl = Math.max(al, bl) + 1;
        
      char[] achar = a.toCharArray();
      char[] bchar = b.toCharArray();
      char[] result= new char[rl];
        
      int min = Math.min(al, bl);
      int i = 1;
      boolean increment = false;
      while (i <= min) {
         boolean ith = (achar[al-i] == '1' ^ bchar[bl-i] == '1') ^ increment;
         increment = (achar[al-i] == '1' && (increment || bchar[bl-i] == '1')) ||
                (increment && bchar[bl-i] == '1');
         result[rl-i] = ith ? '1' : '0';
         i++;
      }
        
      char[] longer = al > bl ? achar : bchar;
      int ll = longer.length;
      while (i <= ll) {
         boolean ith = longer[ll-i] == '1' ^ increment;
         increment = longer[ll-i] == '1' && increment;
         result[rl-i] = ith ? '1' : '0';
         i++;
      }
      if (increment) {
         result[0] = '1';
         return new String(result);
      } else {
         String s = new String(result);
         return s.substring(1);
      }
   }
   
   public void sortColors(int[] nums) {
      int left = 0;
      int right = nums.length-1;
      for (int i = 0; i <= right; i++) {
         if (nums[i] == 0) {
            swap(i, left, nums);
            left++;
            i--;
         } else if (nums[i] == 2) {
            swap(i, right, nums);
            right--;
            i--;
         }
      }
   }
    
   public void swap(int i, int j, int[] nums) {
      if (i != j) {
         int temp = nums[i];
         nums[i] = nums[j];
         nums[j] = temp;
      }
   }
   
   public int removeDuplicates(int[] nums) {
      if (nums.length < 2) {
         return 0;
      }
      int i = 1;
      int left = 1;
      int count = 0;
      while (i < nums.length) {
         
         if (nums[i] == nums[i-1]) {
            nums[left] = nums[i];
            int repeat = nums[i-1];
            count++;
            while (i < nums.length && nums[i] == repeat) {
               count++;
               i++;
            }
            left++;
         } else {
            nums[left] = nums[i];
            i++;
            left++;
         }
      }
      return count;
   }
   
   public String simplifyPath(String path) {
      String[] split = path.split("/");
      Stack<String> s = new Stack<>();
      for (String str : split) {
         if (!str.isEmpty()) {
            if (str.equals("..")) {
               if (!s.isEmpty()) {
                  s.pop();
               }
            } else if (!str.equals("")) {
               s.push(str);
            }
         }
      }
      String result = "";
      while (!s.isEmpty()) {
         result = "/" + s.pop() + result;
      }
      if (result.isEmpty()) {
         result = "/";
      }
      return result;
   
   }
   
   public int maximalRectangle(char[][] matrix) {
      if (matrix.length == 0 || matrix[0].length == 0) {
         return 0;
      }
      int m = matrix.length;
      int n = matrix[0].length;
      int[][] dp = new int[m][n];
      int max = 0;
        
      for (int i = 0; i < n; i++) {
         if (matrix[0][i] == '1') {
            dp[0][i] = 1;
         }
      }
        
      for (int i = 1; i < m; i++) {
         for (int j = 0; j < n; j++) {
            if (matrix[i][j] == '1') {
               dp[i][j] = dp[i-1][j] + 1;
            }
         }
      }
        
      for (int i = 0; i < m; i++) {
         max = Math.max(max, largestRectangleArea(dp[i]));
      }
        
      return max;
   }
    
   public int largestRectangleArea(int[] heights) {
      if (heights.length == 0) 
         return 0;
        
      Stack<int[]> stack = new Stack<>();
      int max = heights[0];
        
      for (int i = 0; i < heights.length; i++) {
         if (stack.isEmpty() || heights[i] >= stack.peek()[1]) {
            stack.push(new int[]{i, heights[i]});
         } else {
            int lastIndex = 0;
            while (!stack.isEmpty() && stack.peek()[1] >= heights[i]) {
               int[] temp = stack.pop();
               lastIndex = temp[0];
               max = Math.max((i-temp[0]) * temp[1], max);
            }
            stack.push(new int[]{lastIndex, heights[i]});
         }
      }
        
      while (!stack.isEmpty()) {
         int[] temp = stack.pop();
         max = Math.max((heights.length-temp[0]) * temp[1], max);
      }
        
      return max;
   }
   
   public ListNode partition(ListNode head, int x) {
      ListNode smallHead = null;
      ListNode largeHead = null;
      ListNode smallEnd = null;
      ListNode largeEnd = null;
      ListNode curr = head;
      while (curr != null) {
         if (curr.val < x) {
            if (smallHead == null) {
               smallHead = curr;
               smallEnd = curr;
               curr = curr.next;
               smallEnd.next = null;
            } else {
               smallEnd.next = curr;
               smallEnd = smallEnd.next;
               curr = curr.next;
               smallEnd.next = null;
            }
         } else {
            if (largeHead == null) {
               largeHead = curr;
               largeEnd = curr;
               curr = curr.next;
               largeEnd.next = null;
            } else {
               largeEnd.next = curr;
               largeEnd = largeEnd.next;
               curr = curr.next;
               largeEnd.next = null;
            }
         }
      }
        
      if (smallEnd == null) {
         return largeHead;
      } else {
         smallEnd.next = largeHead;
         return smallHead;
      }
   
   }
   
   public int[] prisonAfterNDays(int[] cells, int N) {
      if (N == 0) 
         return cells;
        
      Set<String> set = new HashSet<>();
        
      int[] next = nextPrison(cells);
      String str = Arrays.toString(next);
        
      while (!set.contains(str)) {
         System.out.println(str);
         set.add(str);
         next = nextPrison(next);
         str = Arrays.toString(next);
      }
        
      int mod = N % set.size() == 0 ? N : N % set.size();
      next = nextPrison(cells);
      for (int i = 1; i < mod; i++) {
         next = nextPrison(next);
      }
        
      return next;
   }
    
   public int[] nextPrison(int[] curr) {
      int[] next = new int[curr.length];
      for (int i = 1; i < next.length-1; i++) {
         if (curr[i-1] == curr[i+1]) {
            next[i] = 1;
         }
      }
      return next;
   }
   

   
   public void merge(int[] nums1, int m, int[] nums2, int n) {
      if (n == 0) 
         return;
        
      m--;
      n--;
        
      for (int i = n + m + 1; i >= 0; i--) {
         if (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
               nums1[i] = nums1[m];
               m--;
            } else {
               nums1[i] = nums2[n];
               n--;
            }
         } else if (m >= 0) {
            nums1[i] = nums1[m];
            m--;
         } else {
            nums1[i] = nums2[n];
            n--;
         }
      }
        
        
   }
   
   public List<List<Integer>> subsetsWithDup(int[] nums) {
      Arrays.sort(nums);
      List<List<Integer>> result = new ArrayList<>();
      List<Integer> curr = new ArrayList<>();
      subsetDup(nums, result, curr, 0);
      return result;
   }
    
   public void subsetDup(int[] nums, List<List<Integer>> result, 
                         List<Integer> curr, int n) {
      if (n == nums.length) {
         result.add(new ArrayList<>(curr));
      } else {
         int next = n+1;
         while (next < nums.length && nums[next] == nums[n]) {
            next++;
         }
         for (int i = n; i < next; i++) {
            curr.add(nums[i]);
            subsetDup(nums, result, curr, next);
         }
         for (int i = n; i < next; i++) {
            curr.remove(curr.size() - 1);
         }
         subsetDup(nums, result, curr, next);
      
      }
   }
   
         
   public boolean isCycle(int i, Map<Integer, List<Integer>> edgeFrom, boolean[] rec) {
      if (rec[i]) 
         return true;
      List<Integer> nextC = edgeFrom.getOrDefault(i, null);
      if (nextC == null) 
         return false;
      for (int c : nextC) {
         if (isCycle(c, edgeFrom, rec)) 
            return true;
      }
      return false;
   }
   
   public ListNode reverseBetween(ListNode head, int m, int n) {
      if (m == n) 
         return head;
      ListNode reverseStart = null;
      ListNode reverseEnd = null;
      ListNode curr = head;
      for (int i = 1; i < n; i++) {
         if (i == m-1) {
            reverseStart = curr;
         }
         curr = curr.next;
      }
      reverseEnd = curr;
      ListNode reverseEndNext = curr.next;
      if (m == 1) {
         reverse(head, n-m);
         head.next = reverseEndNext;
         head = reverseEnd;
         return head;
      } else {
         ListNode reverseStartNext = reverse(reverseStart.next, n-m);
         reverseStart.next = reverseEnd;
         reverseStartNext.next = reverseEndNext;
         return head;
      }
   }
    
   public ListNode reverse(ListNode curr, int count) {
      if (count == 0) 
         return curr;
      ListNode next = reverse(curr.next, count-1);
      next.next = curr;
      return curr;
   }
   
   public int numTrees(int n) {
      if (n <= 1) 
         return 1;
      int[] dp = new int[n+1];
      dp[0] = 1;
      dp[1] = 1;
      for (int i = 2; i <= n; i++) {
         for (int j = 0; j < i; j++) {
            dp[i] += dp[j] * dp[i-j-1];
         }
      }
      return dp[n];
   }
   
   Map<String, List<String>> cacheWordBreak = new HashMap<>();
   
   public List<String> wordBreak(String s, List<String> wordDict) {
      if (cacheWordBreak.containsKey(s)) {
         return cacheWordBreak.get(s);
      }
      List<String> result = new ArrayList<>();
      for (String str : wordDict) {
         if (s.indexOf(str) == 0) {
            String remain = s.substring(str.length());
            if (remain.isEmpty()) {
               result.add(str);
            } else {
               List<String> nextResult = wordBreak(remain, wordDict);
               for (String r : nextResult) {
                  result.add(str + " " + r);
               }
            }
         }
      }
      cacheWordBreak.put(s, result);
      return result;
   }
   
   public int roomTransport(int n, int[] nums) {
      if (n == 0) 
         return 1;
      int[] dp = new int[n + 1];
      dp[0] = 0;
      for (int i = 1; i < dp.length; i++) {
         dp[i] = dp[i-1]+ (dp[i-1] - dp[nums[i - 1] - 1]) + 2;
      }
      return dp[n] % 1000000007;
   }
   
   /*
     Given an array of integer and a number d,
     return the number of combinations of 3 numbers in the array
     whose max difference is less than or equal to d
   */
   public void choose3WithDiffLTEd() throws FileNotFoundException{
      Scanner in = new Scanner(new File("input.txt"));
      int n = in.nextInt();
      int d = in.nextInt();
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
         nums[i] = in.nextInt();
      }
      Arrays.sort(nums);
      int left = 0;
      int right = 0;
      int res = 0;
      while (right < n || left + 1 < right) {
         if (right < n && nums[right] - nums[left] <= d) {
            right++;
         } else {
            long diff = right - left - 1;
            diff %= 99997867;
            diff = (diff * (diff - 1) / 2) % 99997867;
            res += diff;
            res %= 99997867;
            left++;
         }
      }
      System.out.println(res);
   }
   
}