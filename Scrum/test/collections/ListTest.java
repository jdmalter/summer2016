package collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests classes which implement {@link List}.
 * 
 * @author Jacob Malter
 *
 */
public class ListTest {

   /** stores list implementations */
   @SuppressWarnings("unchecked")
   private final List<Integer>[] lists = (List<Integer>[]) new List<?>[] {
      new ArrayList<Integer>(), new LinkedList<Integer>() };
   /** the number of elements added into each list */
   private static final int SIZE = 100;

   /**
    * Test method for {@link collections.List#add(int, java.lang.Object)}.
    */
   @Test
   public void testAdd() {
      for (List<Integer> list : lists) {

         int half = SIZE / 2;

         // add at front of list
         for (int integer = 0; integer < half; integer++) {
            assertEquals(integer, list.size());
            assertTrue(list.add(0, half - integer - 1));
            assertEquals(integer + 1, list.size());
         }

         // add at back of list
         for (int integer = half + 1; integer < SIZE; integer++) {
            assertEquals(integer - 1, list.size());
            assertTrue(list.add(list.size(), integer));
            assertEquals(integer, list.size());
         }

         // add into middle of list
         assertEquals(SIZE - 1, list.size());
         assertTrue(list.add(half, half));
         assertEquals(SIZE, list.size());

         Boolean b = null;

         try {
            b = list.add(-1, -1);
            fail("i is less than zero");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(b);
         }

         try {
            b = list.add(list.size() + 1, list.size() + 1);
            fail("i is greater than size");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(b);
         }
      }
   }

   /**
    * Test method for {@link collections.List#contains(Object)}.
    */
   @Test
   public void testContains() {
      testAdd();

      for (List<Integer> list : lists) {

         // contains everywhere in list
         for (int integer = 0; integer < SIZE; integer++)
            assertTrue(list.contains(integer));

         // contains outside of list
         assertFalse(list.contains(-1));
      }
   }

   /**
    * Test method for {@link collections.List#get(int)}.
    */
   @Test
   public void testGet() {
      testAdd();

      for (List<Integer> list : lists) {

         // get everywhere in list
         for (int integer = 0; integer < SIZE; integer++)
            assertEquals(integer, list.get(integer).intValue());

         Integer i = null;

         try {
            i = list.get(-1);
            fail("i is less than zero");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(i);
         }

         try {
            i = list.get(list.size());
            fail("i is greater than or equal to size");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(i);
         }
      }
   }

   /**
    * Test method for {@link collections.List#indexOf(java.lang.Object)}.
    */
   @Test
   public void testIndexOf() {
      testAdd();

      for (List<Integer> list : lists) {

         // indexOf everywhere in list
         for (int integer = 0; integer < SIZE; integer++)
            assertEquals(integer, list.indexOf(integer));

         // indexOf outside of list
         assertEquals(-1, list.indexOf(-1));
      }
   }

   /**
    * Test method for {@link collections.List#remove(int)}.
    */
   @Test
   public void testRemove() {
      testAdd();

      for (List<Integer> list : lists) {

         // add and remove into middle of list
         int index = list.size() / 2;
         list.add(index, -1);
         assertEquals(-1, list.remove(index).intValue());

         // remove at front of list
         for (int integer = 0; integer < SIZE / 2; integer++) {
            int size = list.size();
            assertEquals(integer, list.remove(0).intValue());
            assertEquals(size - 1, list.size());
         }

         // remove at back of list
         for (int integer = 0; integer < SIZE / 2; integer++) {
            int size = list.size();
            assertEquals(SIZE - integer - 1,
               list.remove(list.size() - 1).intValue());
            assertEquals(size - 1, list.size());
         }

         Integer i = null;

         try {
            i = list.remove(-1);
            fail("i is less than zero");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(i);
         }

         try {
            i = list.remove(list.size());
            fail("i is greater than or equal to size");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(i);
         }
      }
   }

   /**
    * Test method for
    * {@link collections.List#reverseForEach(utilities.Consumer)}.
    */
   @Test
   public void testReverseForEach() {
      testAdd();

      for (List<Integer> list : lists) {

         Integer[] integers = new Integer[SIZE];
         list.reverseForEach(i -> {
            integers[SIZE - i - 1] = i;
         });

         for (int integer = 0; integer < SIZE; integer++)
            assertEquals(list.get(SIZE - integer - 1), integers[integer]);

         try {
            list.reverseForEach(null);
            fail("consumer is null");
         } catch (NullPointerException ex) {
            // testing for nothing
            for (int integer = 0; integer < SIZE; integer++)
               assertEquals(list.get(SIZE - integer - 1), integers[integer]);
         }
      }
   }

   /**
    * Test method for {@link collections.List#reverseIterator()}.
    */
   @Test
   public void testReverseIterator() {
      testAdd();

      for (List<Integer> list : lists) {

         Integer[] integers = new Integer[SIZE];
         for (Iterator<Integer> it = list.reverseIterator(); it.hasNext();) {
            int i = it.next();
            integers[SIZE - i - 1] = i;
         }

         for (int integer = 0; integer < SIZE; integer++)
            assertEquals(list.get(SIZE - integer - 1), integers[integer]);

         Iterator<Integer> it = list.reverseIterator();
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

   /**
    * Test method for {@link collections.List#set(int, java.lang.Object)}.
    */
   @Test
   public void testSet() {
      testAdd();

      for (List<Integer> list : lists) {

         // add at back of list
         for (int integer = 0; integer < SIZE; integer++) {
            int size = list.size();
            assertEquals(integer, list.set(integer, 2 * integer).intValue());
            assertEquals(size, list.size());
         }

         Integer i = null;

         try {
            i = list.set(-1, -1);
            fail("i is less than zero");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(i);
         }

         try {
            i = list.set(list.size(), list.size());
            fail("i is greater than or equal to size");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(i);
         }
      }
   }

   /**
    * Test method for {@link collections.List#sublist(int, int)}.
    */
   @Test
   public void testSublist() {
      testAdd();

      for (List<Integer> list : lists) {

         int half = list.size() / 2;
         List<Integer> first = list.sublist(0, half - 1);
         List<Integer> second = list.sublist(half, list.size() - 1);

         for (int integer = 0; integer < half; integer++) {
            assertEquals(integer, first.get(integer).intValue());
            assertEquals(integer + half, second.get(integer).intValue());
         }

         List<Integer> l = null;

         try {
            l = list.sublist(-1, list.size() - 1);
            fail("start is less than zero");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(l);
         }

         try {
            l = list.sublist(list.size(), list.size() + 1);
            fail("start is greater than or equal to size");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(l);
         }

         try {
            l = list.sublist(0, list.size());
            fail("end is greater than or equal to size");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(l);
         }

         try {
            l = list.sublist(1, 0);
            fail("start is greater than end");
         } catch (IndexOutOfBoundsException ex) {
            assertNull(l);
         }
      }
   }

   /**
    * Test method for {@link collections.List#swap(int, int)}.
    */
   @Test
   public void testSwap() {
      testAdd();

      for (List<Integer> list : lists) {

         for (int integer = 0; integer < list.size() / 2; integer++)
            list.swap(integer, list.size() - integer - 1);

         for (int integer = 0; integer < SIZE; integer++)
            assertEquals(SIZE - integer - 1, list.get(integer).intValue());

         try {
            list.swap(-1, 0);
            fail("a is less than zero");
         } catch (IndexOutOfBoundsException ex) {
            assertEquals(list.size() - 1, list.get(0).intValue());
         }

         try {
            list.swap(list.size(), 0);
            fail("a is greater than or equal to size");
         } catch (IndexOutOfBoundsException ex) {
            assertEquals(0, list.get(list.size() - 1).intValue());
         }

         try {
            list.swap(0, -1);
            fail("b is less than zero");
         } catch (IndexOutOfBoundsException ex) {
            assertEquals(list.size() - 1, list.get(0).intValue());
         }

         try {
            list.swap(list.size() - 1, list.size());
            fail("b is greater than or equal to size");
         } catch (IndexOutOfBoundsException ex) {
            assertEquals(0, list.get(list.size() - 1).intValue());
         }
      }
   }

}