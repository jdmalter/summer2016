package presenter;

/**
 * In the Observer design pattern, this interface defines the observer. The
 * observer must be able to be notified by its subjects.
 * 
 * In the Model View Presenter pattern, this interface answers "how do events
 * map into changes in my data".
 * 
 * @author Jacob Malter
 *
 */
public interface Observer {

   /**
    * Allow subscribed views to notify the invoking observer.
    */
   void update();

}