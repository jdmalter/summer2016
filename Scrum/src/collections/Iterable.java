package collections;

import java.util.Objects;

/**
 * Function interface which allows an iteration of elements to be consumed by a
 * consumer object.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *        the type of element
 */
public interface Iterable<E> {

   /**
    * Calls method on every element in an iterator.
    * 
    * @param consumer
    *        functional interface to call method on the single parameter
    */
   default void forEach(Consumer<? super E> consumer) {
      Objects.requireNonNull(consumer, "consumer must not be null");
      for (Iterator<E> iterator = iterator(); iterator.hasNext();)
         consumer.consume(iterator.next());
   }

   /**
    * Returns an iterator.
    * 
    * @return an iterator
    */
   Iterator<E> iterator();

}