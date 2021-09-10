/*
You are given a string s of lowercase English letters
and an integer array shifts of the same length.

Call the shift() of a letter, the next letter
in the alphabet, (wrapping around so that 'z' becomes 'a').

For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i + 1 letters of s, x times.

Return the final string after all such shifts to s are applied.

https://leetcode.com/problems/shifting-letters
 */

public class ShiftingLetters {
    static final int ALPHABET = 26;

    public static void main(String[] args) {
        ShiftingLetters solve = new ShiftingLetters();
        String s = "abc";
        int[] shifts = new int[]{3, 5, 9};
        System.out.println(solve.shiftingLetters(s, shifts));
    }

    /**
     * Uses DP to solve. shift[i] = sum(shift[i], shift[i+1], shift[i+2], ...)
     * Hence we use DP from the back.
     * DP[n-1] = shift[n-1]
     * DP[i] = shift[i] + DP[i+1]
     * We also use modulo to keep math simple
     * @param s      the input string
     * @param shifts the shifts on characters
     * @return the shifted string
     */
    public String shiftingLetters(String s, int[] shifts) {
        shifts[shifts.length - 1] %= ALPHABET;
        for (int i = shifts.length - 2; i >= 0; i--) {
            shifts[i] = shifts[i] + shifts[i + 1];
            shifts[i] %= ALPHABET;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = (char) (s.charAt(i) + shifts[i]);
            if (c > 'z') {
                c -= ALPHABET;
            }
            sb.append(c);
        }

        return sb.toString();
    }
}
