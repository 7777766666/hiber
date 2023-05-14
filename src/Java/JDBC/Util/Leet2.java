package Java.JDBC.Util;

import java.util.ArrayList;
import java.util.List;

public class Leet2 {

    public static void main(String[] args) {

        int x = 19;
        System.out.println(isHappy(x));
    }

    List<Integer> list = new ArrayList<>();

    public static boolean isHappy(int n) {

        while (n > 9) {
            int sum = 0;
            int z = 0;

            while (n > 0) {
                z = n % 10;
                sum += z * z;
                n /= 10;
            }
            n = sum;
        }
        return (n == 1 || n == 7) ? true : false;
    }
}
