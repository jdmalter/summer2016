package unqualifiedengineer;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests {@link unqualifiedengineer.HistogramRectangles} class.
 * 
 * @author Jacob Malter
 *
 */
public class HistogramRectanglesTest {

   /** array of inputs */
   private static final int[][] INPUTS =
      new int[][] { {}, { 1 }, { 0, 1, 0, 1, 3, 2, 0, 1, 0, 1 },
         { 1, 3, 2, 1, 2 }, { 5, 1, 1, 1, 1, 1, 0 }, { 5, 4, 3, 2, 1 },
         { 1, 2, 3, 4, 5 }, { 1, 3, 5, 3, 0, 2, 6, 6, 1, 0, 3, 6 },
         { 1, 3, 5, 3, 2, 2, 6, 6, 1, 0, 3, 6 } };
   /** array of expected results from inputs */
   private static final int[] OUTPUTS =
      new int[] { Integer.MIN_VALUE, 1, 4, 5, 6, 9, 9, 12, 14 };

   /**
    * Test method for
    * {@link unqualifiedengineer.HistogramRectangles#findLargestRectangle(int[])}
    * .
    */
   @Test
   public void testFindLargestRectangle() {
      assertEquals(OUTPUTS.length, INPUTS.length);
      for (int position = 0; position < INPUTS.length; position++)
         assertEquals(OUTPUTS[position],
            HistogramRectangles.findLargestRectangle(INPUTS[position]));
   }

}