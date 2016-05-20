package collections;

/**
 * Functional interface to call method on the single parameter.
 * 
 * @author Jacob Malter
 *
 * @param <T>
 *        the type of input
 */
public interface Consumer<T> {

   /**
    * Calls method on the single parameter input.
    * 
    * @param e
    *        the input
    */
   void consume(T t);

}