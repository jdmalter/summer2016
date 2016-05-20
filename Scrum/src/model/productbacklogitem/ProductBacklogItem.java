package model.productbacklogitem;

/**
 * Represents business value that consumes time and attention.
 * 
 * Good Product Backlog Items follow INVEST. Independent: can be scheduled and
 * implemented in any order. Negotiable: contains essence without details; not
 * an explicit contract. Valuable: satisfies the customer. Estimable: judged by
 * size, cost, or time. Small: consumes few person-weeks/days of work. Testable:
 * requires customer tests.
 * 
 * @author Jacob Malter
 *
 */
public interface ProductBacklogItem {

   /**
    * Returns the respective field from the method's name. If the field has not
    * been set, returns the empty string.
    * 
    * @return short text that summarizes the user story and acceptance criteria
    */
   public String getTitle();

   /**
    * Returns the respective field from the method's name. If the field has not
    * been set, returns the empty string.
    * 
    * @return end user description which usually contains who the user is, what
    *         the user wants, and why the wants what they want
    */
   public String getUserStory();

   /**
    * Returns the respective field from the method's name. If the field has not
    * been set, returns the empty string.
    * 
    * @return specific defintion of done
    */
   public String getAcceptanceCriteria();

   /**
    * Sets the respective field to the given parameter. If the given parameter
    * is null, throws NullPointerException.
    * 
    * @param title
    *        new short text that summarizes the user story and acceptance
    *        criteria
    */
   public void setTitle(String title);

   /**
    * Sets the respective field to the given parameter. If the given parameter
    * is null, throws NullPointerException.
    * 
    * @param userStory
    *        new end user description which usually contains who the user is,
    *        what the user wants, and why the wants what they want
    */
   public void setUserStory(String userStory);

   /**
    * Sets the respective field to the given parameter. If the given parameter
    * is null, throws NullPointerException.
    * 
    * @param acceptanceCriteria
    *        new specific defintion of done
    */
   public void setAcceptanceCriteria(String acceptanceCriteria);

}