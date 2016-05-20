package collections;

/**
 * Represents an abstraction for viewing elements.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *        the type of element
 */
public interface Iterator<E> {

   /**
    * Returns true if an iteration is done; false otherwise.
    * 
    * @return true if an iteration is done; false otherwise.
    */
   boolean hasNext();

   /**
    * If an iteration is done (when {@link #hasNext()} returns false), throws
    * NoSuchElementException. Returns the next element in an iteration.
    * 
    * @return the next element in an iteration
    */
   E next();

}