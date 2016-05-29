package utilities;

import java.util.Objects;

/**
 * Functional interface to accept one input and return boolean.
 * 
 * @author Jacob Malter
 *
 * @param <T>
 *        the type of input
 */
public interface Predicate<T> {

   /**
    * Returns and of {@link #test(Object)} from the invoking predicate and the
    * given predicate.
    * 
    * If the given predicate is null, throws NullPointerException.
    * 
    * @param and
    *        other predicate
    * @return and of {@link #test(Object)} from the invoking predicate and the
    *         given predicate
    */
   default Predicate<T> and(Predicate<? super T> and) {
      return t -> test(t)
         && Objects.requireNonNull(and, "and must not be null").test(t);
   }

   /**
    * Returns not of {@link #test(Object)} from the invoking predicate.
    * 
    * @return not of {@link #test(Object)} from the invoking predicate
    */
   default Predicate<T> not() {
      return t -> !test(t);
   }

   /**
    * Returns or of {@link #test(Object)} from the invoking predicate and the
    * given predicate.
    * 
    * If the given predicate is null, throws NullPointerException.
    * 
    * @param or
    *        other predicate
    * @return or of {@link #test(Object)} from the invoking predicate and the
    *         given predicate
    */
   default Predicate<T> or(Predicate<? super T> or) {
      return t -> test(t)
         || Objects.requireNonNull(or, "or must not be null").test(t);
   }

   /**
    * Calls method on one input. Returns true if the Predicate accepts, and
    * returns false otherwise.
    * 
    * @param t
    *        the input
    * @return true if the Predicate accepts, and returns false otherwise
    */
   boolean test(T t);

}