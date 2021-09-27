/*
Given a string s and a dictionary of strings wordDict,
return true if s can be segmented into a space-separated
sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused
multiple times in the segmentation.

https://leetcode.com/problems/word-break/
 */

import java.util.Arrays;
import java.util.List;

public class WordBreak {

    public static void main(String[] args) {
        WordBreak solve = new WordBreak();
        String s = "applepenapple";
        String[] wordDict = new String[]{"apple","pen"};
        System.out.println(solve.wordBreak(s, Arrays.asList(wordDict)));
    }

    /**
     * Uses DP to solve.
     * DP[i] means that string ending at i can be break into the word dict.
     * DP[i] = s[i - w.length, i].equals(w) && DP[i - w.length]
     * @param s string
     * @param wordDict words to use
     * @return true if the string can be composed using the words from word dict.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[0] = true;

        for (int i = 1; i < s.length() + 1; i++) {
            for (String word: wordDict) {
                int idx = i - word.length();
                if (idx >= 0) {
                    if (canBreak[idx] && word.equals(s.substring(idx, i))) {
                        canBreak[i] = true;
                        break;
                    }
                }
            }
        }

        return canBreak[s.length()];
    }
}
