package collections;

import java.util.Objects;

import utilities.Consumer;
import utilities.Predicate;

/**
 * An abstraction for a collection where every element has an integer position
 * from zero to the number of elements. Elements can be accessed and modified by
 * their integer positions.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *        the type of element
 */
public interface List<E> extends Iterable<E> {

   /**
    * Adds the given element to the invoking list (where index equals
    * {@link #size()}). Returns true if an element is added, and returns false
    * otherwise.
    * 
    * @param e
    *        element object
    * @return true if an element is added, and returns false otherwise
    */
   default boolean add(E e) {
      return add(size(), e);
   }

   /**
    * Adds the given element to the invoking list at given index. Every element
    * contained at an index greater than or equal to the given index is shifted
    * higher in index. Returns true if an element is added, and returns false
    * otherwise.
    * 
    * If the given index is less than zero or greater than {@link #size()},
    * throws IndexOutOfBoundsException. Returns true if an element is added, and
    * returns false otherwise.
    * 
    * @param i
    *        position
    * @param e
    *        element object
    * @return true if an element is added, and returns false otherwise
    */
   boolean add(int i, E e);

   /**
    * Returns true if the given element is found in the invoking list, and
    * returns false otherwise.
    * 
    * @param e
    *        element object
    * @return true if the given element is found in the invoking list, and
    *         returns false otherwise
    */
   default boolean contains(E e) {
      return indexOf(e) >= 0;
   }

   /**
    * Returns the element contained at the given index.
    * 
    * If the given index is less than zero or greater than or equal to
    * {@link #size()}, throws IndexOutOfBoundsException.
    * 
    * @param i
    *        position
    * @return the element contained at the given index
    */
   E get(int i);

   /**
    * Returns position of the given element in the invoking list, or if the
    * given element is not found, returns -1.
    * 
    * @param e
    *        element object whose position is returned
    * @return position of the given element in the invoking list, or if the
    *         given element is not found, returns -1
    */
   int indexOf(E e);

   /**
    * This implementation behaves like {@code return #size() == 0}.
    * 
    * @return true if a collection has zero elements (where {@link #size()}
    *         returns 0), and returns false otherwise
    */
   default boolean isEmpty() {
      return size() == 0;
   }

   /**
    * Every element contained at above index of the given element is shifted
    * lower in index. Removes the given element regardless of position. Returns
    * true if an element is removed, and returns false otherwise.
    * 
    * @param e
    *        element object
    * @return true if an element is removed, and returns false otherwise
    */
   boolean remove(E e);

   /**
    * Every element contained at an index greater than to the given index is
    * shifted lower in index. Removes and returns the element contained at the
    * given index.
    * 
    * If the given index is less than zero or greater than or equal to
    * {@link #size()}, throws IndexOutOfBoundsException.
    * 
    * @param i
    *        position
    * @return the element contained at the given index
    */
   E remove(int i);

   /**
    * Calls method on every element in a reverse iterator.
    * 
    * @param consumer
    *        functional interface to call method on the single parameter
    */
   default void reverseForEach(Consumer<? super E> consumer) {
      Objects.requireNonNull(consumer, "consumer must not be null");
      for (Iterator<E> iterator = reverseIterator(); iterator.hasNext();)
         consumer.consume(iterator.next());
   }

   /**
    * Returns an iterator in opposite order to {@link #iterator()}.
    * 
    * @return an iterator in opposite order to {@link #iterator()}
    */
   Iterator<E> reverseIterator();

   /**
    * Adds every element to the iteration that the given predicate accepts into
    * the iterator. Returns an iterator of every element where the predicate
    * accepts the given element in order of {@link #iterator()}.
    * 
    * If the given predicate is null, throws NullPointerException.
    * 
    * @param p
    *        functional interface to accept any superclass of the type of
    *        element and return boolean
    * @return an iterator of every element where the predicate accepts the given
    *         element in order of {@link #iterator()}
    */
   Iterator<E> select(Predicate<? super E> p);

   /**
    * Sets the given index to the given element. Returns the element previously
    * contained at the given index.
    * 
    * If the given index is less than zero or greater than or equal to
    * {@link #size()}, throws IndexOutOfBoundsException.
    * 
    * @param i
    *        position
    * @param e
    *        element object
    * @return the element previously contained at the given index
    */
   E set(int i, E e);

   /**
    * Returns the number of elements.
    * 
    * @return the number of elements
    */
   int size();

   /**
    * Returns a new list containing the same elements contained between the
    * given start and end indices in the invoking list.
    * 
    * If either given index is less than zero or greater than or equal to
    * {@link #size()}, throws IndexOutOfBoundsException. If the given start
    * index is greater than the given end index, throws
    * IndexOutOfBoundsException.
    * 
    * @param start
    *        inclusive start position
    * @param end
    *        inclusive end position
    * @return a new list containing the same elements contained between the
    *         given start and end indices in the invoking list
    */
   List<E> sublist(int start, int end);

   /**
    * Exchanges the elements contained at the given indices. If either given
    * index is less than zero or greater than or equal to {@link #size()},
    * throws IndexOutOfBoundsException.
    * 
    * Array based lists should override this default implementation for better
    * performance.
    * 
    * @param a
    *        first position
    * @param b
    *        second position
    */
   default void swap(int a, int b) {
      E temporary = get(a);
      set(a, get(b));
      set(b, temporary);
   }

}