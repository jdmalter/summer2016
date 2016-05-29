package presenter;

import java.util.Objects;

import model.ProductBacklogItem;
import view.EditView;

/**
 * Refers to a subject and implements behavior to stay consistent with subject.
 * 
 * @author Jacob Malter
 *
 * @param <P>
 *        any subclass of ProductBacklogItem
 */
public class EditObserver<P extends ProductBacklogItem> implements Observer {

   /** stores observers and implements behavior for notifying observers */
   private EditView<P> view;

   /**
    * Sets the respective field to the given parameter. If the given parameter
    * is null, throws NullPointerException.
    * 
    * @param view
    *        Stores observers and implements behavior for notifying observers
    */
   public EditObserver(EditView<P> view) {
      this.view = Objects.requireNonNull(view, "view must not be null");
   }

   @Override
   public void update() {
      System.out.println(view);
   }

}