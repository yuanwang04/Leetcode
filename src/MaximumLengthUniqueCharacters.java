/*
Given an array of strings arr.
String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */

import java.util.*;

public class MaximumLengthUniqueCharacters {

    public static void main(String[] args) {
        MaximumLengthUniqueCharacters solve = new MaximumLengthUniqueCharacters();
        String[] strings = new String[]{"cha","r","act","ers"};
        System.out.println(solve.maxLength(Arrays.asList(strings)));
    }

    public int maxLength(List<String> arr) {
        Set<Character> set = new HashSet<>();
        List<MaskAndCnt> masked = new ArrayList<>();
        for (String s: arr) {
            set.clear();
            boolean valid = true;
            for (int i = 0; i < s.length(); i++) {
                if (set.contains(s.charAt(i))) {
                    valid = false;
                    break;
                } else {
                    set.add(s.charAt(i));
                }
            }
            if (valid) {
                masked.add(new MaskAndCnt(strToBitmask(s), s.length()));
            }
        }

        return maxLength(masked, 0, 0, 0);
    }

    private int maxLength(List<MaskAndCnt> arr, int i, int used, int currLen) {
        if (i >= arr.size()) {
            return currLen;
        }

        MaskAndCnt curr = arr.get(i);
        int using = 0;
        if ((curr.mask & used) == 0) {
            int newUsed = curr.mask | used;
            int newLen = curr.cnt + currLen;
            using = maxLength(arr, i + 1, newUsed, newLen);
        }
        int notUsing = maxLength(arr, i + 1, used, currLen);

        return Math.max(using, notUsing);
    }

    private int strToBitmask(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            int move = str.charAt(i) - 'a';
            res += 1 << move;
        }
        return res;
    }

    static class MaskAndCnt {
        int mask;
        int cnt;

        public MaskAndCnt(int mask, int cnt) {
            this.mask = mask;
            this.cnt = cnt;
        }
    }
}
