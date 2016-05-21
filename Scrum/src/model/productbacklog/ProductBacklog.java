package model.productbacklog;

import collections.Iterable;
import model.productbacklogitem.ProductBacklogItem;

/**
 * Represents a force ranked list of Product Backlog Items. Everyone can query
 * and add to the list, but only the product owner can rank items. The Product
 * Owner and the Development Team negotiate which Product Backlog Items should
 * be moved to the Sprint Backlog.
 *
 * In the Model View Presenter pattern, this interface answers "how do I specify
 * my data" and "how do I change my data".
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
    * Returns an iterable of every Product Backlog Item where getTitle contains
    * the given title, getUserStory contains the given userStory, and
    * getAcceptanceCriteria contains the given acceptanceCriteria from the top
    * to the bottom of the Product Backlog.
    * 
    * This implementation uses {@link String#contains(CharSequence)} to query
    * for Product Backlog Item and returns the same instances of Product Backlog
    * Item that belong to the invoking Product Backlog. Therefore, the empty
    * string accepts every Product Backlog Item, and if any given parameter is
    * null, throws NullPointerException.
    * 
    * Ultimately, I chose Iterable as the return type because I can easily
    * change the underlying data structure without touching this method, and I
    * chose an Iterable over an array because arrays are dangerous to return
    * with generic types. Additionally, Iterable implements
    * {@link Iterable#forEach(collections.Consumer)} to easily interact with
    * Product Backlog Items.
    * 
    * @return an iterable of every Product Backlog Item where getTitle contains
    *         the given title, getUserStory contains the given userStory, and
    *         getAcceptanceCriteria contains the given acceptanceCriteria from
    *         the top to the bottom of the Product Backlog
    */
   Iterable<P> select(String title, String userStory,
      String acceptanceCriteria);

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