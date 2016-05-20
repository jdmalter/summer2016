package model.productbacklogitem;

import java.util.Objects;

/**
 * Extends ProductBacklogItem to include effort implemented by {@link Size}.
 * 
 * @author Jacob Malter
 *
 */
public class SizeProductBacklogItem extends StringProductBacklogItem {

   /**
    * An estimation of cost and time to complete a Product Backlog Item.
    * 
    * @author Jacob Malter
    *
    */
   public static enum Size {

         /** Smaller than medium */
         SMALL,
         /** At most one quarter of a sprint */
         MEDIUM,
         /** Larger than medium */
         LARGE,
         /** At least one whole sprint */
         EXTRA_LARGE;

   }

   /** an estimation of cost and time to complete a Product Backlog Item */
   private Size size = Size.EXTRA_LARGE;

   /**
    * Returns the respective field from the method's name. If the field has not
    * been set, returns {@link Size#EXTRA_LARGE}.
    * 
    * @return an estimation of cost and time to complete a Product Backlog Item
    */
   public Size getSize() {
      return size;
   }

   /**
    * Sets the respective field to the given parameter. If the given parameter
    * is null, throws NullPointerException.
    * 
    * @param size
    *        new estimate of cost and time to complete a Product Backlog Item.
    */
   public void setSize(Size size) {
      this.size = Objects.requireNonNull(size, "size must not be null");
   }

}