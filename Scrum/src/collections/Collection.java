package collections;

/**
 * An abstraction for a group of elements.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *        the type of element
 */
public interface Collection<E> extends Iterable<E> {

   /**
    * Adds the given element into the invoking collection. Returns true if an
    * element is added, and returns false otherwise.
    * 
    * @param e
    *        element object
    * @return true if an element is added, and returns false otherwise
    */
   boolean add(E e);

   /**
    * Returns true if a collection has zero elements (where {@link #size()}
    * returns 0), and returns false otherwise.
    * 
    * This implementation behaves like {@code return #size() == 0}.
    * 
    * @return true if a collection has zero elements (where {@link #size()}
    *         returns 0), and returns false otherwise
    */
   default boolean isEmpty() {
      return size() == 0;
   }

   /**
    * Returns the number of elements.
    * 
    * @return the number of elements
    */
   int size();

}