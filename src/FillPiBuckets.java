/*

    Having n buckets as an array (a);
    Return the minimum water needed to fill 
    any p[i] buckets to same level.

*/

import java.util.*;

public class FillPiBuckets {


   public static void main(String[] args) {
   
      FillPiBuckets test = new FillPiBuckets();
      test.solve(4, 3, new int[]{1,2,3,4}, new int[]{2, 2, 4});
   
   }

   /**
    * 
    * @param n int���� ˮͰ�ĸ���
    * @param q int���� ѯ�ʵĴ���
    * @param a int����һά���� n��ˮͰ�г�ʼˮ�����
    * @param p int����һά���� ÿ��ѯ�ʵ�ֵ
    * @return int����һά����
    */
   public int[] solve (int n, int q, int[] a, int[] p) {
      // write code here
      
      int[] res = new int[q];
      if (q == 0) 
         return res;
      
      Arrays.sort(a);
      for (int i = 0; i < q; i++) {
         res[i] = findMin(n, p[i], a);
      }
      
      return res;
   }
   
   public int findMin(int n, int pi, int[] a) {
      int sum = 0;
      int min = Integer.MAX_VALUE;
      int index = -1;
      for (int i = 0; i < pi - 1; i++) {
         sum += a[i];
      }
      for (int i = pi - 1; i < n; i++) {
         int needed = a[i] * (pi - 1) - sum;
         if (needed < min) {
            index = i;
            min = needed;
         }
         sum -= a[i - (pi - 1)];
         sum += a[i];
      }
      if (min != 0) {
         fill(index, pi, a);
      }
      return min;
   }
   
   public void fill(int index, int pi, int[] a) {
      for (int i = 0; i < pi; i++) {
         a[index - i] = a[index];
      }
   }


}
