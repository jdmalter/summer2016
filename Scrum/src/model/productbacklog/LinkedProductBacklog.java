package model.productbacklog;

import collections.LinkedList;
import model.productbacklogitem.ProductBacklogItem;

/**
 * Implements ProductBacklog with a LinkedList.
 *
 * I prefer ArrayProductBacklog for reasons I state in the header block of
 * {@link model.productbacklog.ArrayProductBacklog}, but if the add and remove
 * methods are invoked more frequently than the swap method, I would prefer this
 * implementation for the same reasons.
 *
 * @author Jacob Malter
 *
 * @param <P>
 *        any subclass of ProductBacklogItem
 */
public final class LinkedProductBacklog<P extends ProductBacklogItem>
   extends ListProductBacklog<P> {

   /**
    * Constructs an empty, new Product Backlog.
    */
   public LinkedProductBacklog() {
      super(new LinkedList<P>());
   }

}