import java.util.*;

public class TencentInterview {

    public static void main(String[] args) {
        String str1 = "12";
        String str2 = "135";
        System.out.println(mul(str1, str2));
        System.out.println(12 * 135);
    }

    public static String mul(String s1, String s2) {
        int[][] temp = new int[s2.length()][s1.length() + s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {
                int a = Integer.parseInt(s2.substring(s2.length() - i - 1, s2.length() - i));
                int b = Integer.parseInt(s1.substring(s1.length() - j - 1, s1.length() - j));
                temp[i][j + i] = a * b;
            }
        }

        StringBuilder builder = new StringBuilder();
        int incre = 0;
        for (int j = 0; j < s1.length() + s2.length(); j++) {
            int sum = incre;
            for (int i = 0; i < s2.length(); i++) {
                sum += temp[i][j];
            }
            incre = sum / 10;
            builder.insert(0, sum % 10);
        }

        while (incre > 0) {
            builder.insert(0, incre % 10);
            incre /= 10;
        }

        while (builder.length() > 0 && builder.charAt(0) == '0') {
            builder.deleteCharAt(0);
        }

        if (builder.length() == 0) {
            return "0";
        }

        return builder.toString();
    }

}
