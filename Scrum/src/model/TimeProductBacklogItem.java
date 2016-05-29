package model;

/**
 * Extends ProductBacklogItem to include effort implemented by time in days.
 * 
 * The use of byte instead of int should highlight that the amount of time in
 * days that might be spent on a Product Backlog Item should be minimized.
 * 
 * @author Jacob Malter
 *
 */
public final class TimeProductBacklogItem extends StringProductBacklogItem {

   /** an estimation of time in days */
   private byte time = 127;

   /**
    * Returns the respective field from the method's name. If the field has not
    * been set, returns 127.
    * 
    * @return an estimation of time in days
    */
   public byte getTime() {
      return time;
   }

   /**
    * Sets the respective field to the given parameter. If the value of the
    * given parameter is less than zero, throws IllegalArgumentException.
    * 
    * @param time
    *        new estimate of time in days
    */
   public void setTime(byte time) {
      if (time < 0)
         throw new IllegalArgumentException("time must not be less than zero");
      this.time = time;
   }

}