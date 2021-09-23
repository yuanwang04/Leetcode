/*
Given a string s representing a valid expression,
implement a basic calculator to evaluate it,
and return the result of the evaluation.

Note: You are not allowed to use any built-in function
which evaluates strings as mathematical expressions, such as eval().

https://leetcode.com/problems/basic-calculator/
 */

import java.util.Stack;

public class BasicCalculator {

    public static void main(String[] args) {
        BasicCalculator solve = new BasicCalculator();
        String input = "(-1)";
        System.out.println(solve.calculate(input));
    }

    public int calculate(String s) {
        s = s.replace(" ", "");
        if (s.isEmpty()) {
            return 0;
        }

        Result result = calculateRecurse(0, s);
        return result.res;
    }

    private Result calculateRecurse(int i, String s) {
        Op op = Op.PLUS;
        int ans = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '+') {
                op = Op.PLUS;
                i++;
            } else if (s.charAt(i) == '-') {
                op = Op.MINUS;
                i++;
            } else if (Character.isDigit(s.charAt(i))) {
                int nexti = readInt(i, s);
                int val = Integer.parseInt(s.substring(i, nexti));
                i = nexti;
                if (op == Op.PLUS) {
                    ans += val;
                } else {
                    ans -= val;
                }
            } else if (s.charAt(i) == '(') {
                Result temp = calculateRecurse(i + 1, s);
                if (op == Op.PLUS) {
                    ans += temp.res;
                } else {
                    ans -= temp.res;
                }
                i = temp.idx;
            } else if (s.charAt(i) == ')') {
                break;
            }
        }

        Result result = new Result();
        result.res = ans;
        result.idx = i + 1;
        return result;
    }

    /**
     * Read an integer from the string from the given position, return the end position
     * @param i the position to start reading
     * @param s the string
     * @return the end position of the integer.
     * The number can be read by Integer.parseInt(s.substring(i, readInt(i, s));
     */
    private int readInt(int i, String s) {
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            i++;
        }
        return i;
    }

    enum Op {
        PLUS,
        MINUS,
    }

    static class Result {
        int idx;
        int res;
    }
}
