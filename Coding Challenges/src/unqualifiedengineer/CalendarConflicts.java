package unqualifiedengineer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * "Given a list of calendar events, determine if any events conflict."
 * 
 * This implementation returns conflicts in sorted order.
 * 
 * There is a conflict between two events when the time intervals of those
 * events overlap, partially or completely. Multiple adjacent conflicts are
 * considered one conflict.
 * 
 * This algorithm runs in O(n) time complexity by traversing events only once.
 * 
 * @see <href>https://youtu.be/olK6SWl8UrM</href>
 * @author Jacob Malter
 *
 */
public final class CalendarConflicts {

   /**
    * An activity on a calendar that occupies a time interval.
    * 
    * @author Jacob Malter
    *
    */
   public static final class Event {

      /** primary identification */
      private final String id;
      /** simplified representation of the start hour */
      private final int start;
      /** simplified representation of the end hour */
      private final int end;

      /**
       * Checks argument validity and sets fields to the respective parameters.
       * 
       * If {@code start >= end} is true, throws IllegalArgumentException, and
       * if {@code id == null} is true, throws NullPointerException.
       * 
       * @param id
       *        primary identification
       * @param start
       *        simplified representation of the start hour
       * @param end
       *        simplified representation of the end hour
       */
      public Event(String id, int start, int end) {
         if (start >= end) throw new IllegalArgumentException(
            "start must not be greater than or equal to end");
         this.id = Objects.requireNonNull(id, "id must not be null");
         this.start = start;
         this.end = end;
      }

   }

   /** an array of sorted events */
   private final Event[] calendar;

   /**
    * Checks for null events and sets calendar field to a copy of the given
    * calendar array.
    * 
    * This implementation assumes that the given calendar is already sorted.
    * Otherwise, any methods will not function as promised.
    * 
    * If the given calendar is null or any event in the given calendar is null,
    * throws NullPointerException.
    * 
    * @param calendar
    *        an array of sorted events
    */
   public CalendarConflicts(Event[] calendar) {
      // Check for null parameters and throw exceptions
      Objects.requireNonNull(calendar, "events must not be null");
      for (Event event : calendar)
         Objects.requireNonNull(event, "individual events must not be null");

      this.calendar = Arrays.copyOf(calendar, calendar.length);
   }

   /**
    * Returns an ordered collection of primary identification for events have
    * conflicts with other events.
    * 
    * @return an ordered collection of primary identification for events have
    *         conflicts with other events
    */
   public List<String> findConflicts() {
      List<String> conflicts = new LinkedList<String>();
      if (calendar.length == 0 || calendar.length == 1) return conflicts;
      // there must be atleast two events in the calendar

      List<String> temporary = new LinkedList<String>();
      // the first event might conflict with the second event
      temporary.add(calendar[0].id);

      int end = calendar[0].start;
      for (int i = 1; i < calendar.length; i++) {

         // since the calendar is sorted, end belongs to the next event
         if (calendar[i].start >= end) { // no conflict

            // flush every temporary conflict to final conflict list since no
            // conflicts will combine at this point in code, also there must be
            // more than one id to create a conflict (atleast two events in a
            // conflict)
            if (temporary.size() > 1) while (temporary.size() > 0)
               conflicts.add(temporary.remove(0));
            temporary.clear(); // useful if there is only one event
         }

         // use the maximum time because the lastest starting event may not have
         // the lastest ending time in a single conflict
         end = Math.max(calendar[i].end, end);
         temporary.add(calendar[i].id); // might conflict with the next event
      }

      // important if there are conflicts at the end of the calendar

      // flush temporary conflicts to final conflicts list since no
      // conflicts will combine at this point in code
      if (temporary.size() > 1) while (temporary.size() > 0)
         conflicts.add(temporary.remove(0));

      return conflicts;
   }

}