/*
    count the largest frames of the continuing traits
    a trait is considered where x1 == x2 && y1 == y2
*/

import java.util.*;
import java.io.*;

public class CatFrame {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner in = new Scanner(new File("input.txt"));
      int n = in.nextInt();
      for (int i = 0; i < n; i++) {
         int frames = in.nextInt();
         int res = 0;
         Map<String, Integer> map = new HashMap<>();
         for (int f = 0; f < frames; f++) {
            int traits = in.nextInt();
            Set<String> thisFrame = new HashSet<>();
            for (int t = 0; t < traits; t++) {
               int x = in.nextInt();
               int y = in.nextInt();
               String key = x + "," + y;
               thisFrame.add(key);
               int curr = map.getOrDefault(key, 0) + 1;
               res = Math.max(res, curr);
               map.put(key, curr);
            }
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
               String key = (String) it.next();
               if (!thisFrame.contains(key)) {
                  it.remove();
               }
            }
         }
         System.out.println(res);
      }
   }
}