package collections;

import java.util.Objects;

import utilities.Consumer;

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
    * Calls method on every element in the invoking iterator.
    * 
    * This implementation calls {@link #next()} until {@link #hasNext()} returns
    * false, therefore elements already returned by {@link #next()} will not be
    * consumed by the given consumer.
    * 
    * @param consumer
    *        functional interface to call method on the single parameter
    */
   default void forEachNext(Consumer<? super E> consumer) {
      Objects.requireNonNull(consumer, "consumer must not be null");
      while (hasNext())
         consumer.consume(next());
   }

   /**
    * @return true if an iteration is done; false otherwise.
    */
   boolean hasNext();

   /**
    * If an iteration is done (when {@link #hasNext()} returns false), throws
    * NoSuchElementException.
    * 
    * @return the next element in an iteration
    */
   E next();

}