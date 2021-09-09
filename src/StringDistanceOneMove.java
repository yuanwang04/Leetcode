import java.util.*;


public class StringDistanceOneMove {

    public static void main(String[] args) {
    
      StringDistanceOneMove sdom = new StringDistanceOneMove();
      sdom.GetMinDistance("aabb", "cdef");
    
    }


    /**
     * 计算最少的距离
     * @param S1 string字符串 第一个字符串
     * @param S2 string字符串 第二个字符串
     * @return int整型
     */
    public int GetMinDistance (String S1, String S2) {
        // write code here
        List<Character>[] dict = new ArrayList[26];
        for (int i = 0; i < S1.length(); i++) {
            int s1char = S1.charAt(i) - 'a';
            if (dict[s1char] == null) {
                dict[s1char] = new ArrayList<>();
            }
            dict[s1char].add(S2.charAt(i));
        }
        
        int globalMax = 0;
        
        for (int i = 0; i < 26; i++) {
            List<Character> list = dict[i];
            if (list != null) {
                int[] cnt = new int[26];
                int localMax = 0;
                for (Character c : list) {
                    cnt[c - 'a']++;
                    localMax = Math.max(localMax, cnt[c - 'a']);
                }
                globalMax = Math.max(globalMax, localMax);
            }
        }
        
        return countDistance(S1, S2) - globalMax;
        
        
    }
    
    public int countDistance(String s1, String s2) {
        int a = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                a++;
            }
        }
        return a;
    }
}