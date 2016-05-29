package view;

import collections.LinkedList;
import collections.List;
import model.ProductBacklogItem;
import presenter.Observer;

/**
 * Stores observers and implements behavior for notifying observers.
 * 
 * @author Jacob Malter
 *
 * @param <P>
 *        any subclass of ProductBacklogItem
 */
public class EditView<P extends ProductBacklogItem> implements View {

   /** list of observers which require status updates */
   private final List<Observer> observers = new LinkedList<Observer>();

   @Override
   public boolean add(Observer observer) {
      return observers.add(observer);
   }

   @Override
   public void notifyObservers() {
      observers.forEach(o -> o.update());
   }

   @Override
   public boolean remove(Observer observer) {
      return observers.remove(observer);
   }

}