
import java.util.*;

public class DependencyList {
    public static void main(String[] args) {
        String input = "8,2,7,4,6,-1,5,5,6";
        DependencyList d = new DependencyList();
        System.out.println(d.compileSeq(input));
    }

    public String compileSeq(String input) {
        // write code here
        // parsing
        String[] temp = input.split(",");
        int[] dep = new int[temp.length];
        for (int i = 0; i < dep.length; i++) {
            dep[i] = Integer.parseInt(temp[i]);
        }
        System.out.println(Arrays.toString(dep));

        // building tree
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < dep.length; i++) {
            recurse(dep, output, i);
        }

        if (output.size() == 0) {
            return "";
        } else {
            StringBuilder result = new StringBuilder();
            for (int n : output) {
                result.append(n);
                result.append(",");
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
    }

    public void recurse(int[] dep, List<Integer> output, int idx) {
        if (dep[idx] < 0) {
            if (dep[idx] == -1) {
                output.add(idx);
                dep[idx] = -2;
            }
        } else {
            recurse(dep, output, dep[idx]);
            output.add(idx);
            dep[idx] = -2;
        }
    }
}
