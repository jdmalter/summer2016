package model.productbacklog;

import java.util.Objects;

import collections.Iterable;
import collections.LinkedList;
import collections.List;
import model.productbacklogitem.ProductBacklogItem;

/**
 * An abstract class that implements ProductBacklog with a List interface. It is
 * left to subclasses to provide a list implementation.
 *
 * This class was created after I noticed code duplication between
 * ArrayProductBacklog and LinkedProductBacklog which both stored their list
 * implementation under the List interface.
 * 
 * @author Jacob Malter
 *
 */
abstract class ListProductBacklog<P extends ProductBacklogItem>
   implements ProductBacklog<P> {

   /** underyling data structure with integer positions */
   private final List<P> list;

   /**
    * This implementation can create a Product Backlog constructed with Product
    * Backlog Items if the given list already contains Product Backlog Items.
    *
    * @param list
    *        any implementation of the List interface
    */
   protected ListProductBacklog(List<P> list) {
      this.list = Objects.requireNonNull(list, "list must not be null");
   }

   @Override
   public void add(P item) {
      // index size is bottom of Product Backlog
      list.add(Objects.requireNonNull(item, "item must not be null"));
   }

   @Override
   public Iterable<P> select(String title, String userStory,
      String acceptanceCriteria) {
      // check for null parameters that cause contains to throw an exception
      Objects.requireNonNull(title, "title must not be null");
      Objects.requireNonNull(userStory, "userStory must not be null");
      Objects.requireNonNull(acceptanceCriteria,
         "acceptanceCriteria must not be null");

      // LinkedList because every operation is an insertion
      List<P> selection = new LinkedList<P>();

      list.forEach(item -> {
         if (item.getTitle().contains(title)
            && item.getUserStory().contains(userStory)
            && item.getAcceptanceCriteria().contains(acceptanceCriteria))
            selection.add(item);
      });

      return selection;
   }

   @Override
   public P remove() {
      if (list.isEmpty())
         throw new IllegalStateException("Product Backlog must not be empty");
      return list.remove(0); // index of zero is top of Product Backlog
   }

   @Override
   public void swap(int index) {
      if (index < 0)
         throw new IllegalStateException("index must not be negative");

      else if (index >= list.size() - 1) throw new IllegalStateException(
         "index must be less than the number of elements in the Product Backlog minus one");

      list.swap(index, index + 1); // index + 1 is below index
   }

}