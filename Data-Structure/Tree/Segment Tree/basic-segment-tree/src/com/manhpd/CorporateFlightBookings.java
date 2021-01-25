package com.manhpd;

/**
 * There are n flights, and they are labeled from 1 to n.
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 *
 * Example 1:
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 *
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 * 1 <= bookings[i][2] <= 10000
 */
public class CorporateFlightBookings {

    public static void main(String[] args) {
        int[][] bookings = {
                {1, 2, 10},
                {2, 3, 20},
                {2, 5, 25}
        };
        int n = 5;

        CorporateFlightBookings solution = new CorporateFlightBookings();
        int[] seats = solution.corpFlightBookings(bookings, n);
        System.out.println(seats);
    }

    /**
     * Use Segment tree to solve
     *
     * @param bookings - the number of bookings
     * @param n - the number of flights
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        return new int[]{};
    }


}
