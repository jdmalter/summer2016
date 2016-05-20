package model.productbacklogitem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link TimeProductBacklogItem} class.
 * 
 * @author Jacob Malter
 *
 */
public class TimeProductBacklogItemTest {

   /** TimeProductBaclogItems reset between tests */
   private TimeProductBacklogItem[] items;

   /**
    * Constructs new instances of TimeProductBacklogItem.
    * 
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      items = new TimeProductBacklogItem[] { new TimeProductBacklogItem(),
         new TimeProductBacklogItem(), new TimeProductBacklogItem(),
         new TimeProductBacklogItem() };
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.TimeProductBacklogItem#getTime()}.
    */
   @Test
   public void testGetTime() {
      for (TimeProductBacklogItem item : items)
         assertEquals(127, item.getTime());
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.TimeProductBacklogItem#setTime(byte)}.
    */
   @Test
   public void testSetTime() {
      for (TimeProductBacklogItem item : items) {

         byte time = 0;
         item.setTime(time);
         assertNotEquals(127, item.getTime());
         assertEquals(time, item.getTime());

         try {
            item.setTime((byte) -1); // eclipse required me to place this cast
            fail("time is less than zero");
         } catch (IllegalArgumentException ex) {
            assertEquals(time, item.getTime());
         }
      }
   }

}