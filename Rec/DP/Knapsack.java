import java.util.Arrays;

public class Knapsack {

    /**
     * O(2^n).
     *
     * @param w: weights
     * @param v: values
     * @param n: num elems
     * @param W: total capacity
     * @return: best possible value
     */
    int knapsackRec(int[] w, int[] v, int n, int W) {
        if (n <= 0) {
            return 0;
        // surpasses the weight
        } else if (w[n - 1] > W) {
            return knapsackRec(w, v, n - 1, W); // stick to the best solution so far
        // does fit, then choose best between
        } else {
            return Math.max(
                knapsackRec(w, v, n - 1, W - w[n - 1]) + v[n - 1], // (best solution so far - weight) + value of current item
                knapsackRec(w, v, n - 1, W) // best solution so far
            );
        }
    }

    /**
     * Linearize recursive(exp) to O(nW).
     *
     * @param w: weights
     * @param v: values
     * @param n: num elems
     * @param W: total capacity
     * @return: best possible value
     */
    int knapsackDP(int[] w, int[] v, int n, int W) {
        if (n <= 0 || W <= 0) {
            return 0;
        }

        // store results of sub-problems
        int[][] m = new int[n + 1][W + 1];
        Arrays.stream(m[0]).forEach(j -> j = 0);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (w[i - 1] > j) {
                    m[i][j] = m[i - 1][j]; // previous best
                } else {
                    m[i][j] = Math.max(
                        m[i - 1][j], // previous best
                        m[i - 1][j - w[i - 1]] + v[i - 1]); // pre-calculated optimal + value of current item
                }
            }
        }
        return m[n][W];
    }

}
