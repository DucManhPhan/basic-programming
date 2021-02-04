package com.manhpd;

/**
 * http://codeforces.com/contest/380/problem/C
 *
 * Sereja has a bracket sequence s1, s2,...,sn, or, in other words, a string s of length n, consisting of characters "(" and ")".
 * Sereja needs to answer m queries, each of them is described by two integers li,ri (1≤li≤ri≤n).
 * The answer to the i-th query is the length of the maximum correct bracket subsequence of sequence sli,sli+1,...,sri. Help Sereja answer all queries.
 * You can find the definitions for a subsequence and a correct bracket sequence in the notes.
 *
 * Input
 * The first line contains a sequence of characters s1,s2,...,sn (1≤n≤106) without any spaces.
 * Each character is either a "(" or a ")". The second line contains integer m (1≤m≤105) — the number of queries.
 * Each of the next m lines contains a pair of integers. The i-th line contains integers li,ri (1≤li≤ri≤n) — the description of the i-th query.
 *
 * Output
 * Print the answer to each question on a single line. Print the answers in the order they go in the input.
 *
 * Examples
 * Input
 * ())(())(())(
 * 7
 * 1 1
 * 2 3
 * 1 2
 * 1 12
 * 8 12
 * 5 11
 * 2 10
 *
 * Output
 * 0
 * 0
 * 2
 * 10
 * 4
 * 6
 * 6
 *
 */
public class SerejaAndBrackets {

    public static void main(String[] args) {
        String brackets = "())(())(())(";
        int[][] queries = {
                {0, 0},
                {1, 2},
                {0, 1},
                {0, 11},
                {7, 11},
                {4, 11},
                {1, 9}
        };

//        String brackets = "(()))";
//        int[][] queries = {
//                {0, 4},
//                {1, 2},
//                {1, 3}
//        };

        BracketsTree tree = new BracketsTree(brackets);

        for (int i = 0; i < queries.length; ++i) {
            int[] query = queries[i];
            System.out.println(tree.query(query[0], query[1]));
        }

    }

    private static class BracketsTree {
        private Node[] segTree;
        private char[] brackets;

        public BracketsTree(String brackets) {
            if (brackets == null || brackets.isEmpty()) {
                throw new RuntimeException("Input does not have data");
            }

            this.brackets = brackets.toCharArray();
            this.segTree = new Node[4 * brackets.length()];
            this.buildBracketsTree(0, 0, brackets.length() - 1);
        }

        private void buildBracketsTree(int idxSegTree, int start, int end) {
            if (start == end) {
                if (this.brackets[start] == '(') {
                    this.segTree[idxSegTree] = new Node(0, 1, 0);
                } else {
                    this.segTree[idxSegTree] = new Node(0, 0, 1);
                }

                return;
            }

            int mid = start + (end - start) / 2;
            this.buildBracketsTree(2 * idxSegTree + 1, start, mid);
            this.buildBracketsTree(2 * idxSegTree + 2, mid + 1, end);

            this.segTree[idxSegTree] = this.combine(this.segTree[2 * idxSegTree + 1],
                                                    this.segTree[2 * idxSegTree + 2]);
        }

        public Node query(int qStart, int qEnd) {
            if (qStart >= this.brackets.length || qEnd < 0) {
                return new Node(0, 0, 0);
            }

            return this.query(0, 0, this.brackets.length - 1, qStart, qEnd);
        }

        private Node query(int idxSegTree, int start, int end, int qStart, int qEnd) {
            // outside
            if (qStart > end || qEnd < start) {
                return new Node(0, 0, 0);
            }

            // inside: qStart --- start --- end --- qEnd
            if (qStart <= start && end <= qEnd) {
                return this.segTree[idxSegTree];
            }

            int mid = start + (end - start) / 2;
            Node left = this.query(2 * idxSegTree + 1, start, mid, qStart, qEnd);
            Node right = this.query(2 * idxSegTree + 2, mid + 1, end, qStart, qEnd);

            return this.combine(left, right);
        }

        private Node combine(Node left, Node right) {
            int tmp = Math.min(left.openBrackets, right.closeBrackets);

            int optimal = left.optimal + right.optimal + tmp;
            int open = left.openBrackets +right.openBrackets - tmp;
            int close = left.closeBrackets + right.closeBrackets - tmp;

            return new Node(optimal, open, close);
        }

        private static class Node {
            public int optimal;
            public int openBrackets;
            public int closeBrackets;

            public Node(int optimal, int openBrackets,
                        int closeBrackets) {
                this.optimal = optimal;
                this.openBrackets = openBrackets;
                this.closeBrackets = closeBrackets;
            }

            public String toString() {
                return this.optimal + " - " + this.openBrackets + " - " + this.closeBrackets;
            }
        }
    }

}
