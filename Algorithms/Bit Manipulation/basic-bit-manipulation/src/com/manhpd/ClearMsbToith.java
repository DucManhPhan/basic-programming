package com.manhpd;

/**
 * Clear all bits from MSB to ith bit
 *
 * mask = (1 << i) - 1;
 * x &= mask;
 */
public class ClearMsbToith {

    public static void main(String[] args) {
        int x = 29;
        int tmp = clearMsbToithBit(x, 4);

        System.out.println(tmp);
    }

    public static int clearMsbToithBit(int x, int ithBit) {
        int mask = (1 << ithBit) - 1;
        return x & mask;
    }

}
