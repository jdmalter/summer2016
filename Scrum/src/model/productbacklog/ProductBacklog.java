package model.productbacklog;

import collections.Iterator;
import model.productbacklogitem.ProductBacklogItem;

/**
 * Represents a force ranked list of Product Backlog Items. Everyone can query
 * and add to the list, but only the product owner can rank items. The Product
 * Owner and the Development Team negotiate which Product Backlog Items should
 * be moved to the Sprint Backlog.
 *
 * @author Jacob Malter
 *
 * @param <P>
 *        any subclass of ProductBacklogItem
 */
public interface ProductBacklog<P extends ProductBacklogItem> {

   /**
    * Adds a Product Backlog Item at the bottom of the Product Backlog. If the
    * given item is null, throws NullPointerException.
    * 
    * @param item
    *        business value that consumes time and attention
    */
   void add(P item);

   /**
    * Returns an iterator of Product Backlog Items from the top to the bottom of
    * the Product Backlog.
    * 
    * This implementation returns the same instances of Product Backlog Item
    * that belong to the invoking Product Backlog.
    * 
    * Ultimately, I chose Iterator as the return type because I can easily
    * change the underlying data structure without touching this method, and I
    * chose an iterator over an array because arrays are dangerous to return
    * with generic types.
    * 
    * Only this method queries the Product Backlog, and every other method
    * changes the Product Backlog. In other words, only this method is a
    * selection, and every other method is a command.
    * 
    * @return an iterator of Product Backlog Items from the top to the bottom of
    *         the Product Backlog
    */
   Iterator<P> iterator();

   /**
    * Removes and returns a Product Backlog Item from the top of the Product
    * Backlog. If the Product Backlog is empty, throws IllegalStateException.
    * 
    * @return a Product Backlog Item from the top of the Product Backlog
    */
   P remove();

   /**
    * Exchanges the adjacent Product Backlog Items at the given index and below
    * the given index. The given index points to the Product Backlog Item that
    * is closer to the top of the Product Backlog than the Product Backlog Item
    * below the given index. If the given index is negative or greater than or
    * equal to the number of elements in the Product Backlog minus one, throws
    * IllegalStateException.
    * 
    * @param index
    *        position in Product Backlog
    */
   void swap(int index);

}