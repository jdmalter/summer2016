package model.productbacklog;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import collections.Iterator;
import model.productbacklog.ArrayProductBacklog;
import model.productbacklog.LinkedProductBacklog;
import model.productbacklog.ProductBacklog;
import model.productbacklogitem.ProductBacklogItem;
import model.productbacklogitem.SizeProductBacklogItem;
import model.productbacklogitem.TimeProductBacklogItem;

/**
 * Tests classes which implement {@link ProductBacklog}.
 * 
 * @author Jacob Malter
 *
 */
public class ProductBacklogTest {

   /** ProductBacklog reset between tests */
   private ProductBacklog<ProductBacklogItem>[] productBacklogs;
   /** ProductBaclogItems added into ProductBacklog */
   private ProductBacklogItem[] items = new ProductBacklogItem[] {
      new SizeProductBacklogItem(), new SizeProductBacklogItem(),
      new SizeProductBacklogItem(), new TimeProductBacklogItem(),
      new TimeProductBacklogItem(), new TimeProductBacklogItem() };

   /**
    * Constructs new instances of ProductBacklog.
    * 
    * @throws java.lang.Exception
    */
   @SuppressWarnings("unchecked")
   @Before
   public void setUp() throws Exception {
      productBacklogs =
         (ProductBacklog<ProductBacklogItem>[]) new ProductBacklog<?>[] {
            new ArrayProductBacklog<ProductBacklogItem>(),
            new LinkedProductBacklog<ProductBacklogItem>() };
   }

   /**
    * Test method for
    * {@link model.productbacklog.ArrayProductBacklog#add(model.StringProductBacklogItem.ProductBacklogItem)}
    * .
    */
   @Test
   public void testAdd() {
      for (ProductBacklog<ProductBacklogItem> productBacklog : productBacklogs) {

         for (ProductBacklogItem item : items)
            productBacklog.add(item);

         try {
            productBacklog.add(null);
            fail("item is null");
         } catch (NullPointerException ex) {

         }
      }
   }

   /**
    * Test method for
    * {@link model.productbacklog.ArrayProductBacklog#iterator()}.
    */
   @Test
   public void testIterator() {
      testAdd();

      for (ProductBacklog<ProductBacklogItem> productBacklog : productBacklogs) {

         int index = 0;
         for (Iterator<ProductBacklogItem> iterator = productBacklog.iterator();
            iterator.hasNext();) {
            assertTrue(items[index++] == iterator.next());
         }
      }
   }

   /**
    * Test method for {@link model.productbacklog.ArrayProductBacklog#remove()}.
    */
   @Test
   public void testRemove() {
      testAdd();

      for (ProductBacklog<ProductBacklogItem> productBacklog : productBacklogs) {

         for (int i = 0; i < items.length; i++)
            assertTrue(items[i] == productBacklog.remove());

         ProductBacklogItem item = null;

         try {
            item = productBacklog.remove();
            fail("Product Backlog is empty");
         } catch (IllegalStateException ex) {
            assertNull(item);
         }
      }
   }

   /**
    * Test method for
    * {@link model.productbacklog.ArrayProductBacklog#swap(int)}.
    */
   @Test
   public void testSwap() {
      testAdd();

      for (ProductBacklog<ProductBacklogItem> productBacklog : productBacklogs) {

         try {
            productBacklog.swap(-1);
            fail("index is negative");
         } catch (IllegalStateException ex) {
         }

         try {
            productBacklog.swap(items.length - 1);
            fail("index is greater than or equal to the number of elements "
               + "in the Product Backlog minus one");
         } catch (IllegalStateException ex) {
         }

         // Reverse list
         for (int i = items.length - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
               productBacklog.swap(j);

         for (int i = 0; i < items.length; i++)
            assertTrue(items[items.length - i - 1] == productBacklog.remove());
      }
   }

}