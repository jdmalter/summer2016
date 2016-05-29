package view;

import presenter.Observer;

/**
 * In the Observer design pattern, this interface defines the subject. The
 * subject must know its observers and be able to add and remove observers.
 * 
 * In the Model View Presenter pattern, this interface answers "how do I display
 * my data".
 * 
 * @author Jacob Malter
 *
 */
public interface View {

   /**
    * The given observer will be notified when {@link #notifyObservers()} is
    * invoked. Returns true if an observer is added, and returns false
    * otherwise.
    * 
    * @param observer
    *        refers to this view and implements behavior to stay consistent with
    *        the invoking view
    * @return true if an observer is added, and returns false otherwise
    */
   boolean add(Observer observer);

   /**
    * The given observer will not be notified when {@link #notifyObservers()} is
    * invoked. Returns true if an observer is removed, and returns false
    * otherwise.
    * 
    * @param observer
    *        refers to this view and implements behavior to stay consistent with
    *        the invoking view
    * @return true if an observer is removed, and returns false otherwise
    */
   boolean remove(Observer observer);

   /**
    * Let interested observers know when to query the invoking view.
    */
   void notifyObservers();

}