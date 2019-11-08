package com.manhpd;


import java.util.ArrayList;
import java.util.List;

/**
 * C(0) = 1; C(n + 1) = sigma->i(0, n) of C(i)C(n - i) with n >= 0.
 *
 */
public class CatalanNumber {

    public static void main(String[] args) {
        int size = 10;
        List<Long> lstResult = new ArrayList<Long>(size) {{
            add(1L);
            for (int i = 0; i < size - 1; ++i) {
                add(0L);
            }
        }};

        long startTime = System.currentTimeMillis();
        System.out.println("Result of " + String.valueOf(size) + "th catalan number is: " + getCatalanNumber_Memoization(lstResult, size - 1));

//        for (int i = 0; i < 10; ++i) {
//            System.out.println("Result of " + String.valueOf(size) + " catalan number is: " + getCatalanNumber_Recursive(size));
//        }

        long endTime = System.currentTimeMillis();
        long ellapsedTime = endTime - startTime;
//        System.out.println("Execution time of utilizing Recursion for catalan number is: " + String.valueOf(ellapsedTime) + " in milliseconds");
        System.out.println("Execution time of utilizing Memoization for catalan number is: " + String.valueOf(ellapsedTime) + " in milliseconds");
    }

    private static long getCatalanNumber_Recursive(int num) {
        if (num <= 0) {
            return 1;
        }

        long result = 0;
        for (int i = 0; i < num; ++i) {
            result += getCatalanNumber_Recursive(i) * getCatalanNumber_Recursive(num - i - 1);
        }

        return result;
    }

    private static long getCatalanNumber_Memoization(List<Long> lstResult, int num) {
        if (num <= 0 || lstResult.get(num) > 0) {
            return lstResult.get(num);
        }

        long result = 0;
        for (int i = 0; i < num; ++i) {
            result += getCatalanNumber_Memoization(lstResult, i) * getCatalanNumber_Memoization(lstResult, num - i - 1);
        }

        lstResult.set(num, result);
        return result;
    }

    private static long getCatalanNumber_Tabulation() {


        return 0;
    }

}
