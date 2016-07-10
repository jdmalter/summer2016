package partition;

import java.math.BigInteger;

/**
 * "Given a positive integer n, find how many ways to write n as the sum of
 * positive integers."
 *
 * This implementation uses naive recursion.
 *
 * @author Jacob Malter
 */
public final class Partition {

	/**
	 * Calls {@link #partition(int, int)} with a k equal to one.
	 * 
	 * This implementation behaves as if {@code return partition(n, 1);}.
	 *
	 * @param n
	 *            integer
	 * @param k
	 *            integer
	 *
	 * @return the number of partitions
	 */
	public static BigInteger partition(int n) {
		return partition(n, 1);
	}

	/**
	 * Computes the number of partitions of n where the smallest integer is k.
	 * This implementation uses naive recursion. Returns the number of
	 * partitions.
	 *
	 * If {@code n <= 0} or {@code k <= 0} is true, throws
	 * IllegalArgumentException.
	 *
	 * The defintion of a partition is here
	 * {@link https://en.wikipedia.org/wiki/Partition_(number_theory)}.
	 *
	 * This algorithm is based on
	 * {@link http://www.ces.clemson.edu/~jimlb/Teaching/Math573/Math573partitions1.pdf}
	 *
	 * @param n
	 *            integer
	 * @param k
	 *            integer
	 *
	 * @return the number of partitions
	 */
	public static BigInteger partition(int n, int k) {
		if (n <= 0)
			throw new IllegalArgumentException("n must be positive but was " + n);
		else if (k <= 0)
			throw new IllegalArgumentException("k must be positive but was " + k);
		else
			return partitionRecursive(n, k);
	}

	/**
	 * If {@code n < k} is true, returns 0. If {@code n == k} is true, returns
	 * 1. Otherwise, returns the sum of {@code partition(n, k + 1)} and
	 * {@code partition(n - k, k)}.
	 *
	 * This implementation assumes its parameters are positive integers.
	 *
	 * @param n
	 *            asserted positive integer
	 * @param k
	 *            asserted positive integer
	 *
	 * @return the number of partitions
	 */
	private static BigInteger partitionRecursive(int n, int k) {
		if (n < k)
			return BigInteger.ZERO;
		else if (n == k)
			return BigInteger.ONE;
		// smallest integer must be k or greater than k
		else
			return partitionRecursive(n, k + 1).add(partitionRecursive(n - k, k));
	}

}