import java.util.*;

/*
* Return a list of list of integer whose sum equal to the given target
* Time complexity: O(n^(k-1))
*/


public class KSum {

   // k-sum
   public List<List<Integer>> twoSum(int[] nums, int target, int i) {
      List<List<Integer>> result = new ArrayList<>();
      int j = nums.length - 1;
      while (i < j) {
         int sum = nums[i] + nums[j];
         if (sum == target) {
            List<Integer> oneAnswer = new ArrayList<>();
            oneAnswer.add(nums[i]);
            oneAnswer.add(nums[j]);
            result.add(oneAnswer);
            i++;
            j--;
            while (i < j && nums[j] == nums[j+1]) {
               j--;
            }
         } else if (sum < target) {
            i++;
         } else {
            j--;
         }
      }
      return result;
   }

   public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
      if (k == 2) {
         return twoSum(nums, target, start);
      }
      List<List<Integer>> result = new ArrayList<>();
      int chosen = start;
      while (chosen < nums.length - 2) {
         List<List<Integer>> lst = kSum(nums, target - nums[chosen], chosen+1, k-1);
         for (List<Integer> oneSolution : lst) {
            oneSolution.add(nums[chosen]);
         }
         result.addAll(lst);
         chosen++;
         while (chosen < nums.length-2 && nums[chosen] == nums[chosen-1]) {
            chosen++;
         }
      }
      return result;
   }

}
