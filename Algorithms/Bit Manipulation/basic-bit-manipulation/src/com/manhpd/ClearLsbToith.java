package com.manhpd;

/**
 * Clear all bits from LSB to ith bit
 *
 * mask = ~((1 << (i + 1) - 1);
 * x &= mask
 */
public class ClearLsbToith {

    public static void main(String[] args) {
        int x = 29;
        int tmp = clearLsbToithBit(x, 3);

        System.out.println(tmp);
    }

    public static int clearLsbToithBit(int x, int ithBit) {
        int mask = ~((1 << (ithBit + 1)) - 1);
        return x & mask;
    }

}
