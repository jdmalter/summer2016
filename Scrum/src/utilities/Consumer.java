package utilities;

/**
 * Functional interface to accept one input and return nothing.
 * 
 * @author Jacob Malter
 *
 * @param <T>
 *        the type of input
 */
public interface Consumer<T> {

   /**
    * Returns combintation of {@link #consume(Object)} from the invoking
    * consumer followed by the given consumer.
    * 
    * If the given consumer is null, throws NullPointerException.
    * 
    * @param after
    *        other consumer
    * @return combintation of {@link #consume(Object)} from the invoking
    *         consumer followed by the given consumer
    */
   default Consumer<T> after(Consumer<? super T> after) {
      return t -> {
         consume(t);
         after.consume(t);
      };
   }

   /**
    * Calls method on one input.
    * 
    * @param t
    *        the input
    */
   void consume(T t);

}