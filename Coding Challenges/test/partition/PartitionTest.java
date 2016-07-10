package partition;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

/**
 * Tests {@link partition.Partition} class.
 * 
 * @author Jacob Malter
 *
 */
public class PartitionTest {

   /**
    * Test method for {@link partition.Partition#partition(int, int)}.
    */
   @Test
   public void testPartition() {
      BigInteger test = null;

      try {
         test = Partition.partition(0);
         fail("n is less than or equal to zero");
      } catch (IllegalArgumentException ex) {
         assertNull(test);
      }

      try {
         test = Partition.partition(1, 0);
         fail("k is less than or equal to zero");
      } catch (IllegalArgumentException ex) {
         assertNull(test);
      }

      // integer k of 1
      helpPartition("1", 1);
      helpPartition("2", 2);
      helpPartition("3", 3);
      helpPartition("7", 5);
      helpPartition("22", 8);
      helpPartition("2323520", 66);

      // given integer k
      helpPartition("9", 12, 3);
      helpPartition("16", 24, 6);
      helpPartition("1", 36, 36);
      helpPartition("0", 48, 60);
   }

   /**
    * Asserts that a big integer created with val and partition of n are equal.
    * 
    * @param val
    *        decimal String representation of BigInteger
    * @param n
    *        integer
    */
   private void helpPartition(String val, int n) {
      assertEquals(new BigInteger(val), Partition.partition(n));
   }

   /**
    * Asserts that a big integer created with val and partition of n and k are
    * equal.
    * 
    * @param val
    *        decimal String representation of BigInteger.
    * @param n
    *        integer
    * @param k
    *        integer
    */
   private void helpPartition(String val, int n, int k) {
      assertEquals(new BigInteger(val), Partition.partition(n, k));
   }

}