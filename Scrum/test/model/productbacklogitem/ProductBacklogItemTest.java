package model.productbacklogitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests classes which implement {@link ProductBacklogItem}.
 * 
 * @author Jacob Malter
 *
 */
public class ProductBacklogItemTest {

   /** ProductBackogItems reset between tests */
   private ProductBacklogItem[] items;

   /**
    * Constructs new instances of ProductBacklogItem.
    * 
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      items = new ProductBacklogItem[] { new SizeProductBacklogItem(),
         new TimeProductBacklogItem() };
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.ProductBacklogItem#getTitle()}.
    */
   @Test
   public void testGetTitle() {
      for (ProductBacklogItem item : items)
         assertEquals("", item.getTitle());
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.ProductBacklogItem#getUserStory()}.
    */
   @Test
   public void testGetUserStory() {
      for (ProductBacklogItem item : items)
         assertEquals("", item.getUserStory());
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.ProductBacklogItem#getAcceptanceCriteria()}
    * .
    */
   @Test
   public void testGetAcceptanceCriteria() {
      for (ProductBacklogItem item : items)
         assertEquals("", item.getAcceptanceCriteria());
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.ProductBacklogItem#setTitle(java.lang.String)}
    * .
    */
   @Test
   public void testSetTitle() {
      for (ProductBacklogItem item : items) {

         item.setTitle("title");
         assertNotEquals("", item.getTitle());
         assertEquals("title", item.getTitle());

         try {
            item.setTitle(null);
            fail("title is null");
         } catch (NullPointerException ex) {
            assertEquals("title", item.getTitle());
         }
      }
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.ProductBacklogItem#setUserStory(java.lang.String)}
    * .
    */
   @Test
   public void testSetUserStory() {
      for (ProductBacklogItem item : items) {

         item.setUserStory("userStory");
         assertNotEquals("", item.getUserStory());
         assertEquals("userStory", item.getUserStory());

         try {
            item.setUserStory(null);
            fail("userStory is null");
         } catch (NullPointerException ex) {
            assertEquals("userStory", item.getUserStory());
         }
      }
   }

   /**
    * Test method for
    * {@link model.productbacklogitem.ProductBacklogItem#setAcceptanceCriteria(java.lang.String)}
    * .
    */
   @Test
   public void testSetAcceptanceCriteria() {
      for (ProductBacklogItem item : items) {

         item.setAcceptanceCriteria("acceptanceCriteria");
         assertNotEquals("", item.getAcceptanceCriteria());
         assertEquals("acceptanceCriteria", item.getAcceptanceCriteria());

         try {
            item.setAcceptanceCriteria(null);
            fail("acceptanceCriteria is null");
         } catch (NullPointerException ex) {
            assertEquals("acceptanceCriteria", item.getAcceptanceCriteria());
         }
      }
   }

}