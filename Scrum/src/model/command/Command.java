package model.command;

/**
 * Functional interface to call method on its state.
 * 
 * In the Model View Presenter pattern, this interface answers "how do I change
 * my data".
 * 
 * @author Jacob Malter
 * 
 * @param <T>
 *        any type of class
 */
public interface Command<T> {

   /**
    * Invokes the command on its state.
    */
   void execute();

}