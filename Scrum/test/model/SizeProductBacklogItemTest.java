package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.SizeProductBacklogItem;
import model.SizeProductBacklogItem.Size;

/**
 * Tests {@link SizeProductBacklogItem} class.
 * 
 * @author Jacob Malter
 *
 */
public class SizeProductBacklogItemTest {

   /** SizeProductBaclogItems reset between tests */
   private SizeProductBacklogItem[] items;

   /**
    * Constructs new instances of SizeProductBacklogItem.
    * 
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      items = new SizeProductBacklogItem[] { new SizeProductBacklogItem(),
         new SizeProductBacklogItem(), new SizeProductBacklogItem(),
         new SizeProductBacklogItem() };
   }

   /**
    * Test method for
    * {@link model.SizeProductBacklogItem#getSize()}.
    */
   @Test
   public void testGetSize() {
      for (SizeProductBacklogItem item : items)
         assertEquals(Size.EXTRA_LARGE, item.getSize());
   }

   /**
    * Test method for
    * {@link model.SizeProductBacklogItem#setSize(model.SizeProductBacklogItem.Size)}
    * .
    */
   @Test
   public void testSetSize() {
      for (SizeProductBacklogItem item : items) {

         Size size = Size.SMALL;
         item.setSize(size);
         assertNotEquals(Size.EXTRA_LARGE, item.getSize());
         assertEquals(size, item.getSize());

         try {
            item.setSize(null);
            fail("size is null");
         } catch (NullPointerException ex) {
            assertEquals(size, item.getSize());
         }
      }
   }

}