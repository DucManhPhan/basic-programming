package com.manhpd;

/**
 * Find log base 2 of 32 bit integer
 */
public class CalculateLog2 {

    public static void main(String[] args) {
//        int n = 16;
        int n = 6;
        int res = log2(n);

        System.out.println(res);
    }

    public static int log2(int x)
    {
        int res = 0;
        while ((x >>= 1) != 0) {
            res++;
        }

        return res;
    }

}
