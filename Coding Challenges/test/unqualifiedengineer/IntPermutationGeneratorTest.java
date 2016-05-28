package unqualifiedengineer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.Test;

/**
 * Tests {@link unqualifiedengineer.IntPermutationGenerator} class.
 * 
 * @author Jacob Malter
 *
 */
public class IntPermutationGeneratorTest {

   /** class used for testing purposes */
   private IntPermutationGenerator generator;
   /** array of expected results from generate for size of 1 to 4 */
   int[][][] expected = { {}, {}, { { 1, 2 }, { 2, 1 } },
      { { 1, 2, 3 }, { 1, 3, 2 }, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 },
         { 3, 2, 1 } },
      { { 1, 2, 3, 4 }, { 1, 2, 4, 3 }, { 1, 3, 2, 4 }, { 1, 3, 4, 2 },
         { 1, 4, 2, 3 }, { 1, 4, 3, 2 }, { 2, 1, 3, 4 }, { 2, 1, 4, 3 },
         { 2, 3, 1, 4 }, { 2, 3, 4, 1 }, { 2, 4, 1, 3 }, { 2, 4, 3, 1 },
         { 3, 1, 2, 4 }, { 3, 1, 4, 2 }, { 3, 2, 1, 4 }, { 3, 2, 4, 1 },
         { 3, 4, 1, 2 }, { 3, 4, 2, 1 }, { 4, 1, 2, 3 }, { 4, 1, 3, 2 },
         { 4, 2, 1, 3 }, { 4, 2, 3, 1 }, { 4, 3, 1, 2 }, { 4, 3, 2, 1 } } };

   /**
    * Test method for correctness for
    * {@link unqualifiedengineer.IntPermutationGenerator#generate()}.
    */
   @Test
   public void testGenerate() {
      // only cover size inputs with expected results
      for (int size = 2; size < 5; size++) {

         // test for obvious side effects
         generator = null;
         generator = new IntPermutationGenerator(size);
         assertNotNull(generator);

         // test the contents of the generator
         for (int p = 0; p < expected[size].length; p++)
            equals(expected[size][p], generator.generate());
      }
   }

   /**
    * Test method for exceptions for
    * {@link unqualifiedengineer.IntPermutationGenerator#generate()}.
    */
   @Test
   public void testGenerateExceptions() {
      // test what happens if the size is invalid

      generator = null;

      try {
         generator = new IntPermutationGenerator(1);
         fail("size is less than or equal to one");
      } catch (IllegalArgumentException ex) {
         assertNull(generator);
      }

      // test what happens after every permutation has been generated

      // set up to generate every permutation
      int size = 10; // around 12, generate becomes really slow
      generator = new IntPermutationGenerator(size);

      for (BigInteger calls = BigInteger.ZERO, limit = factorial(size);
         calls.compareTo(limit) < 0; calls = calls.add(BigInteger.ONE))
         generator.generate();

      int[] base = null; // testing purposes

      try {
         base = generator.generate();
         fail("no more permutations to be generated");
      } catch (IllegalStateException ex) {
         assertNull(base);
      }
   }

   /**
    * Compares the two given arrays of equality of length and elements.
    * 
    * @param expected
    *        what the given array should be equal to
    * @param actual
    *        what the given array actually is
    */
   private static void equals(int[] expected, int[] actual) {
      assertEquals(expected.length, actual.length);
      for (int index = 0; index < expected.length; index++)
         assertEquals(expected[index], actual[index]);
   }

   /**
    * Computes n!. When {@code n < 0} is true, throws IllegalArgumentException.
    * 
    * <p>
    * This implmentation uses naive recursion, but generating every permutation
    * practically prevents this methods from being called with large integers.
    * However, actual testing reveals that this implementation is very fast for
    * integers around a few thousand (far above practical for testing any O(n!)
    * algorithm).
    * 
    * @param n
    *        non-negative integer
    * @return non-negative BigInteger
    */
   private static BigInteger factorial(int n) {
      if (n < 0)
         throw new IllegalArgumentException("n must not be less than zero");

      else if (n == 0 || n == 1) return BigInteger.ONE; // by convention

      else return BigInteger.valueOf(n).multiply(factorial(n - 1));
   }

}