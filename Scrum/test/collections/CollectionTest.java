package collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests classes which implement both {@link Collection} and {@link Iterable}.
 * 
 * @author Jacob Malter
 *
 */
public class CollectionTest {

   /** stores collection implementations */
   @SuppressWarnings("unchecked")
   private final Collection<Integer>[] collections =
      (Collection<Integer>[]) new Collection<?>[] { new ArrayList<Integer>(),
         new LinkedList<Integer>() };
   /** the number of elements added into each collection */
   private static final int SIZE = 100;

   /**
    * Test method for {@link collections.Collection#add(Object)}.
    */
   @Test
   public void testAdd() {
      for (Collection<Integer> collection : collections) {

         for (int integer = 0; integer < SIZE; integer++) {
            assertEquals(integer, collection.size());
            assertTrue(collection.add(integer));
            assertEquals(integer + 1, collection.size());
         }
      }
   }

   /**
    * Test method for {@link collections.Collection#isEmpty()}.
    */
   @Test
   public void testIsEmpty() {
      for (Collection<Integer> collection : collections)
         assertTrue(collection.isEmpty());

      testAdd();

      for (Collection<Integer> collection : collections)
         assertFalse(collection.isEmpty());
   }

   /**
    * Test method for {@link collections.Collection#size()}.
    */
   @Test
   public void testSize() {
      for (Collection<Integer> collection : collections)
         assertEquals(0, collection.size());

      testAdd();

      for (Collection<Integer> collection : collections)
         assertEquals(SIZE, collection.size());
   }

   /**
    * Test method for
    * {@link collections.Iterable#forEach(utilities.Consumer)}.
    */
   @Test
   public void testForEach() {
      testAdd();

      for (Collection<Integer> collection : collections) {

         Integer[] integers = new Integer[SIZE];
         collection.forEach(i -> {
            integers[SIZE - i - 1] = i;
         });

         for (int integer = 0; integer < SIZE; integer++)
            assertTrue(-1 < integers[integer]);
      }
   }

   /**
    * Test method for {@link collections.Iterable#iterator()}.
    */
   @Test
   public void testIterator() {
      testAdd();

      for (Collection<Integer> collection : collections) {

         Integer[] integers = new Integer[SIZE];
         int index = 0;
         for (Iterator<Integer> it = collection.iterator(); it.hasNext();) {
            int i = it.next();
            integers[index++] = i;
         }

         for (int integer = 0; integer < SIZE; integer++)
            assertTrue(-1 < integers[integer]);

         Iterator<Integer> it = collection.iterator();
         for (; it.hasNext(); it.next());
         Integer i = null;

         try {
            it.next();
            fail("the iteration is done");
         } catch (NoSuchElementException ex) {
            assertNull(i);
         }
      }
   }

}