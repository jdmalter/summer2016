package collections;

import java.util.NoSuchElementException;

/**
 * Implements list interface with an array. When the number of elements equals
 * one less than the length of the array, the array's capacity is doubled.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *        the type of element
 */
public class ArrayList<E> implements List<E> {

   /** initial length of the array */
   private static final int INITIAL_CAPACITY = 31;
   /** string given to exception when set, get, or remove is invalid */
   private static final String INVALID_ACCESS_INDEX = "Invalid index for"
      + " accessing element to list. Index must be non-negative and"
      + " strictly less than size.";
   /** string given to exception when add is invalid */
   private static final String INVALID_ADD_INDEX = "Invalid index for adding"
      + " element to list. Index must be non-negative and less than or"
      + " equal to size.";

   /** stores elements */
   @SuppressWarnings("unchecked")
   private E[] elements = (E[]) new Object[INITIAL_CAPACITY];
   /** the number of elements */
   private int size;

   @Override
   public boolean add(int i, E e) {
      if (i < 0 || i > size)
         throw new IndexOutOfBoundsException(INVALID_ADD_INDEX);

      if (size == elements.length - 1) {
         // suppress safe since only elements of type E will be added
         @SuppressWarnings("unchecked")
         E[] copy = (E[]) new Object[2 * elements.length];

         for (int n = 0; n < elements.length; n++)
            copy[n] = elements[n];
         elements = copy;
      }

      // shift position of every element after i
      for (int n = size - 1; n >= i; n--)
         elements[n + 1] = elements[n];
      elements[i] = e;
      size++;
      return true;
   }

   @Override
   public E get(int i) {
      if (i < 0 || i >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      // this implementation is fast
      return elements[i];
   }

   @Override
   public int indexOf(E e) {
      for (int i = 0; i < size; i++)
         if (elements[i] == null ? e == null : elements[i].equals(e)) return i;

      // the given element is not found
      return -1;
   }

   @Override
   public E remove(int i) {
      if (i < 0 || i >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      E result = elements[i];
      // shift position of every element after i
      for (int n = i; n < size - 1; n++)
         elements[n] = elements[n + 1];
      elements[--size] = null;
      return result;
   }

   @Override
   public Iterator<E> reverseIterator() {
      return new Iterator<E>() {

         /** start iteration at the last element */
         private int cursor = size - 1;

         @Override
         public boolean hasNext() {
            return cursor >= 0;
         }

         @Override
         public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[cursor--];
         }

      };
   }

   @Override
   public E set(int i, E e) {
      if (i < 0 || i >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      E result = elements[i];
      elements[i] = e;
      return result;
   }

   @Override
   public List<E> sublist(int start, int end) {
      if (start < 0) throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      else if (start >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      else if (end >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      else if (start > end) throw new IndexOutOfBoundsException(
         "start must be less than or equal to end");

      // create list regardless of position
      List<E> list = new ArrayList<E>();

      // start where index equals the given start position. after the given
      // start position, add each element until the given end position
      for (int i = start; i <= end; i++)
         list.add(elements[i]);

      return list;
   }

   @Override
   public void swap(int start, int end) {
      if (start < 0 || start >= size || end < 0 || end >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      E temporary = elements[start];
      elements[start] = elements[end];
      elements[end] = temporary;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public Iterator<E> iterator() {
      return new Iterator<E>() {

         /** start iteration at the first element */
         private int cursor = 0;

         @Override
         public boolean hasNext() {
            return cursor < size;
         }

         @Override
         public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[cursor++];
         }

      };
   }

}