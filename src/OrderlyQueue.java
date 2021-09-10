/*
You are given a string s and an integer k.
You can choose one of the first k letters of s and move it to the end of the string.

Return the lexicographically smallest string you could have
after applying the mentioned step any number of moves.

https://leetcode.com/problems/orderly-queue/
 */

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrderlyQueue {

    public static void main(String[] args) {
        OrderlyQueue solve = new OrderlyQueue();
        System.out.println(solve.orderlyQueue("abcabc", 2));
    }

    /**
     * Notice that if k > 1, then any permutation is possible.
     * Because any two characters can be swapped.
     * If k = 1, then it takes O(N^2) to find the answer.
     * @param s the string
     * @param k the switch
     * @return the smallest string
     */
    public String orderlyQueue(String s, int k) {
        if (k == 0) {
            return s;
        }
        if (k == 1) {
            return smallestRotation(s);
        }

        SortedMap<Character, Integer> counter = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.putIfAbsent(s.charAt(i), 0);
            counter.put(s.charAt(i), counter.get(s.charAt(i)) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (char c: counter.keySet()) {
            sb.append(String.valueOf(c).repeat(counter.get(c)));
        }
        return sb.toString();
    }

    private String smallestRotation(String s) {
        String smallest = s;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length() - 1; i++) {
            char c = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(c);
            if (sb.toString().compareTo(smallest) < 0) {
                smallest = sb.toString();
            }
        }

        return smallest;
    }
}
