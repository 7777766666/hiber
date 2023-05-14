package Java.JDBC.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode {

    public static void main(String[] args) {

        int[] y = {10, 1, 2,  6, 7, 1, 5};
        int z = 8;

        System.out.println(combinationSum2(y, z));
    }

    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        System.out.print(Arrays.toString(candidates));
        System.out.println("  "  + target);
        int length = candidates.length;
        int f = length;
        int c = length -1;
        for (int i = 0; i < length ; i++) {
            if (candidates[0] + candidates[i + 1] > target){
              c =  i;
              i = 666;
            }
        }

        System.out.println(candidates[c] +" c");



        int z = 0;
        while (z != c){
            if (candidates[z] + candidates[c] == target){
                list.add(Arrays.asList(candidates[z], candidates[c]));
            }
            z++;
        }


        return list;
    }

}
