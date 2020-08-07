/*

Quicksort: swap the array in place.
O(NlogN) for time and O(1) for space.

In this case, quicksort the given arr by the second element, increasing. 

*/


public class Quicksort {

   public static void quicksort(int[][] arr, int i, int j) {
      if (i < j) {
         int pi = partition(arr, i, j);
         quicksort(arr, i, pi-1);
         quicksort(arr, pi+1, j);
      }
   }

   public static int partition(int[][] arr, int i, int j) {
      int pivot = arr[j][0];
      int left = i;
      for (int right = i; right < j; right++) {
         if (arr[right][0] <= pivot) {
            int[] temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
            left++;
         }
      }
      int[] temp = arr[j];
      arr[j] = arr[left];
      arr[left] = temp;
      return left;
   }

}
