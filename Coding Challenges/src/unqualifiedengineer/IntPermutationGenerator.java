package unqualifiedengineer;

import java.util.Arrays;

/**
 * "A generator that will return a new permutation of the integers between 1 and
 * size untill all permutations have been exhausted."
 * 
 * This implementation returns permutations in lexicographical order.
 * 
 * There are three details that help solve this problem.
 * <ol>
 * <li>Integers are in reverse sorted order until there is a decrease.</li>
 * <li>Integers after the point of reversal must be reversed to obtain the next
 * permutation.</li>
 * <li>Th next biggest integer in the reverse sorted tail after the decrease
 * integer must be swapped with the decrease integer</li>
 * </ol>
 * 
 * Since there are n! permutations on n integers and each permutation takes O(n)
 * time to generate, generating every permutation with this algorithm takes
 * O(n!*n) time complexity.
 * 
 * @see <href>https://youtu.be/V7hHupttzVk</href>
 * @author Jacob Malter
 *
 */
public final class IntPermutationGenerator {

	/** contains permutations of integers between 1 and size */
	private final int[] base;

	/**
	 * Constructs a generator with the given size. When {@code size <= 1} is
	 * true, throws IllegalArgumentException.
	 * 
	 * @param size
	 *            signed integer greater than one
	 */
	public IntPermutationGenerator(int size) {
		if (size <= 1)
			throw new IllegalArgumentException("size must not be less than or equal to one");

		// zeros cannot exist in any return from this permutation generator
		// since
		// every integer must be atleast two
		base = new int[size];
	}

	/**
	 * Adds integers from 1 to size in the base array. Called on the first
	 * invocation of {@link #generate()}.
	 */
	private int[] fill() {
		for (int index = 0; index < base.length; index++)
			base[index] = index + 1;

		// prevent idiots from modifying private fields
		return Arrays.copyOf(base, base.length);
	}

	/**
	 * Standard swap method given two positions of integers in the base array.
	 * 
	 * @param a
	 *            position of first integer in the base array
	 * @param b
	 *            position of second integer in the base array
	 */
	private void swap(int a, int b) {
		int temporary = base[a];
		base[a] = base[b];
		base[b] = temporary;
	}

	/**
	 * Creates permutations in lexicographical order. If there are no more
	 * permutations to be generated, throws IllegalStateException.
	 * 
	 * @return permutations of integers between 1 and size
	 */
	public int[] generate() {
		// index 0 is valid because base length must be atleast two
		if (base[0] == 0)
			return fill();

		int decrease = base.length - 2; // furthest right position of decrease
		// partition is compared to the element greater than itself, therefore
		// the
		// last two elements in being compared must be base.length - 1 and
		// base.length - 2. Between those elements, base.length - 2 is smaller.

		// loop finds any decrease of two numbers in base array unless the
		// numbers
		// are reverse sorted like n, n - 1, n - 2, ... 1, but if the numbers
		// are
		// reversed sorted as given, every other permutation must have already
		// been generated
		while (base[decrease] > base[decrease + 1] && decrease >= 0) {
			if (decrease == 0)
				throw new IllegalStateException("no more permutations to be generated");

			// old partition was not decreasing, so move on
			decrease--;
		}

		int larger = base.length - 1; // right end of positions for larger
		while (base[larger] < base[decrease]) {
			larger--;
		}
		// after loop has broken, it must be true that base[larger] >
		// base[decrease], and we know that there must be SOME larger number in
		// the tail end of the base since there was a decrease between decrease
		// and decrease + 1

		swap(larger, decrease);
		int right = base.length - 1;// right end of the base array
		int left = decrease + 1; // left end of the tail in the base array

		// reverses all positions between the inital left and right positions
		while (left < right) {
			swap(left, right);
			left++; // move torward right
			right--; // move torward left
		}

		// prevent idiots from modifying private fields
		return Arrays.copyOf(base, base.length);
	}

}