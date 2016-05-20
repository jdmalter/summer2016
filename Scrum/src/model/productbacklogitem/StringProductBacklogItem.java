package model.productbacklogitem;

import java.util.Objects;

/**
 * An abstract class that implements ProductBacklog with Strings. It would be
 * possible to implement ProductBacklogItem with an equivalent to string like
 * char[] or charSequence.
 * 
 * The use of abstract class should highlight that additional state for effort
 * must be added so that every Product Backlog Item becomes easily estimable.
 * 
 * This class must be extended to add additional state such as effort. Some
 * implementations can represent effort as a number, but other implementations
 * can represent effort as a enum. Ideally, effort should be relative or
 * ordinal.
 * 
 * @author Jacob Malter
 *
 */
abstract class StringProductBacklogItem implements ProductBacklogItem {

   /** short text that summarizes the user story and acceptance criteria */
   private String title = "";
   /**
    * end user description which usually contains who the user is, what the user
    * wants, and why the wants what they want
    */
   private String userStory = "";
   /** specific defintion of done */
   private String acceptanceCriteria = "";

   @Override
   public String getTitle() {
      return title;
   }

   @Override
   public String getUserStory() {
      return userStory;
   }

   @Override
   public String getAcceptanceCriteria() {
      return acceptanceCriteria;
   }

   @Override
   public void setTitle(String title) {
      this.title = Objects.requireNonNull(title, "title must not be null");
   }

   @Override
   public void setUserStory(String userStory) {
      this.userStory =
         Objects.requireNonNull(userStory, "userStory must not be null");
   }

   @Override
   public void setAcceptanceCriteria(String acceptanceCriteria) {
      this.acceptanceCriteria = Objects.requireNonNull(acceptanceCriteria,
         "acceptanceCriteria must not be null");
   }

}