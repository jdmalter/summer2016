package unqualifiedengineer;

import java.util.Objects;
import java.util.Stack;

/**
 * "Finds the largest rectangle within a histogram".
 * 
 * This implementation finds area in linear time complexity.
 * 
 * @see <href>https://youtu.be/VNbkzsnllsU</href>
 * @author Jacob Malter
 *
 */
public final class HistogramRectangles {

	/**
	 * I REALLY want java auto-boxing/unboxing and lambda functions that accept
	 * non-final/effectively final arguments.
	 * 
	 * @author Jacob Malter
	 *
	 */
	private static final class Int {

		/** a non-final/effectively final 32 bit signed integer */
		private int i;

		/**
		 * Sets the respective field to the given parameter.
		 * 
		 * @param i
		 *            a non-final/effectively final 32 bit signed integer
		 */
		private Int(int i) {
			this.i = i;
		}

	}

	/**
	 * Functional interface that accepts no inputs and returns nothing.
	 * Potentially has side effects.
	 * 
	 * @author Jacob Malter
	 *
	 */
	private interface Function {

		/**
		 * Calls method on no inputs.
		 */
		void execute();

	}

	/**
	 * If the length of the given array of int is 0, returns the minimum integer
	 * value. Returns area of the largest rectangle.
	 * 
	 * If the given array of int is null, throws NullPointerException, and if
	 * any int contained by the array of int is negative, throws
	 * IllegalArgumentException.
	 * 
	 * @param histogram
	 *            array representation where indexes are the x-axis positions
	 *            and int values are the y-axis heights
	 * @return area of the largest rectangle
	 */
	public static int findLargestRectangle(int[] histogram) {
		// validate input
		Objects.requireNonNull(histogram, "histogram must not be null");
		for (int i : histogram)
			if (i < 0)
				throw new IllegalArgumentException("i must not be negative");

		// because Java forces me to
		Int height = new Int(0), position = new Int(0), tempHeight = new Int(0), tempPosition = new Int(0),
				tempSize = new Int(0), maxSize = new Int(Integer.MIN_VALUE);

		// track all rectangles that might be larger

		// height of possible rectangles which might not actually be the height
		// of the current position
		Stack<Int> heights = new Stack<Int>();

		// right end of possible rectangles
		Stack<Int> positions = new Stack<Int>();

		// at this point in code, i must ask myself "Why Java?"

		// helper function that has access to scope to generate multiple side
		// effects instead of returned variables
		Function popThatShit = () -> {
			tempHeight.i = heights.pop().i;
			tempPosition.i = positions.pop().i;

			// size of new largest position rectangle
			tempSize.i = tempHeight.i * (position.i - tempPosition.i);
			maxSize.i = Math.max(tempSize.i, maxSize.i);
		};

		for (; position.i < histogram.length; position.i++) {
			height = new Int(histogram[position.i]);

			// add first height or new, taller height
			if (heights.isEmpty() || height.i > heights.peek().i) {
				// regardless of position, add these values to the stack
				heights.push(new Int(height.i));
				positions.push(new Int(position.i));

			} else if (height.i < heights.peek().i) {
				// check possible rectanlges whose heights are smaller
				while (!heights.isEmpty() && height.i < heights.peek().i)
					popThatShit.execute();
				// after this loop, we reached a position in the stack where the
				// current height in the stack is less than the current height
				// that
				// we care about

				// regardless of position, return those values to the stack
				heights.push(new Int(height.i));
				positions.push(new Int(tempPosition.i)); // not position!
				// this push allows us to "look back in time" at older
				// rectangles
			}
		}

		// use loop to find area of rectangles that continue to the end of the
		// histrogram and find the largest area
		while (!heights.isEmpty())
			popThatShit.execute();
		return maxSize.i;
	}

}