package model.productbacklog;

import collections.ArrayList;
import model.productbacklogitem.ProductBacklogItem;

/**
 * Implements ProductBacklog with an ArrayList.
 *
 * I prefer ArrayList because I predict that the ProductBacklog will run faster.
 * If the swap method is invoked more frequently than the add and remove
 * methods, it is reasonable to focus on minimizing the runtime of the swap
 * method. Since the swap method relies on List's get and set methods, I want to
 * use ArrayList which runs much faster than LinkedList.
 *
 * @author Jacob Malter
 *
 * @param <P>
 *        any subclass of ProductBacklogItem
 */
public final class ArrayProductBacklog<P extends ProductBacklogItem>
   extends ListProductBacklog<P> {

   /**
    * Constructs an empty, new Product Backlog.
    */
   public ArrayProductBacklog() {
      super(new ArrayList<P>());
   }

}