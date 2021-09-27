/*
Given a string s and a dictionary of strings wordDict, add spaces
in s to construct a sentence where each word is a valid dictionary
word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple
times in the segmentation.

https://leetcode.com/problems/word-break-ii/
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakII {

    public static void main(String[] args) {
        WordBreakII solve = new WordBreakII();
        String s = "catsanddog";
        String[] wordDict = new String[]{"cat","cats","and","sand","dog"};
        System.out.println(solve.wordBreak(s, Arrays.asList(wordDict)));
    }

    /**
     * Uses DP to solve.
     * DP[i] means all the word that can form the sentence ending at i.
     * Then uses backtrace from DP[s.length()] to find all possible combinations.
     * @param s the string
     * @param wordDict the wordDict
     * @return a list of space-separated words that forms String s.
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<List<String>> dp = new ArrayList<>(s.length() + 1);
        dp.add(new ArrayList<>());
        dp.get(0).add("");

        for (int i = 1; i < s.length() + 1; i++) {
            dp.add(new ArrayList<>());
            for (String word: wordDict) {
                int idx = i - word.length();
                if (idx >= 0) {
                    if (!dp.get(idx).isEmpty() && word.equals(s.substring(idx, i))) {
                        dp.get(i).add(word);
                    }
                }
            }
        }

        List<String> result = new ArrayList<>();
        if (dp.get(s.length()).isEmpty()) {
            return result;
        }

        for (String word: dp.get(s.length())) {
            StringBuffer curr = new StringBuffer();
            curr.append(word);
            backtrace(result, curr, dp, s.length() - word.length());
        }

        return result;
    }

    private void backtrace(List<String> result, StringBuffer curr, List<List<String>> dp, int idx) {
        if (idx == 0) {
            result.add(curr.toString());
            return;
        }

        for (String word: dp.get(idx)) {
            curr.insert(0, word + " ");
            backtrace(result, curr, dp, idx - word.length());
            curr.delete(0, word.length() + 1);
        }
    }

}
