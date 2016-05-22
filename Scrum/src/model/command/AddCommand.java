package model.command;

import java.util.Objects;

import model.productbacklog.ProductBacklog;
import model.productbacklogitem.ProductBacklogItem;

/**
 * When executed, adds the given Product Backlog Item to the given Product
 * Backlog.
 * 
 * @author Jacob Malter
 *
 * @param <P>
 *        any subclass of ProductBacklogItem
 */
final class AddCommand<P extends ProductBacklogItem> implements Command<P> {

   /** a force ranked list of Product Backlog Items */
   private final ProductBacklog<P> productBacklog;
   /** business value that consumes time and attention */
   private final P item;

   /**
    * Accepts the given Product Backlog into which the given Product Backlog
    * Item will be added upon execution.
    * 
    * @param productBacklog
    *        a force ranked list of Product Backlog Items
    * @param item
    *        business value that consumes time and attention
    */
   protected AddCommand(ProductBacklog<P> productBacklog, P item) {
      this.productBacklog = Objects.requireNonNull(productBacklog,
         "productBacklog must not be null");
      this.item = Objects.requireNonNull(item, "item must not be null");
   }

   @Override
   public void execute() {
      productBacklog.add(item);
   }

}