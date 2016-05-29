package unqualifiedengineer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * Tests {@link unqualifiedengineer.DinnerParties} class.
 * 
 * @author Jacob Malter
 *
 */
public class DinnerPartiesTest {

   /** input list of friends used to create results */
   private final List<String> friends = new ArrayList<String>();
   /** set of expected combinations from find for one case */
   private static final Set<Set<String>> EXPECTED = new HashSet<Set<String>>();

   static {
      Set<String> set;

      set = new HashSet<String>();
      set.add("a");
      set.add("b");
      set.add("c");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("a");
      set.add("b");
      set.add("d");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("a");
      set.add("b");
      set.add("e");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("a");
      set.add("c");
      set.add("d");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("a");
      set.add("c");
      set.add("e");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("a");
      set.add("d");
      set.add("e");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("b");
      set.add("c");
      set.add("d");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("b");
      set.add("c");
      set.add("e");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("b");
      set.add("d");
      set.add("e");
      EXPECTED.add(set);

      set = new HashSet<String>();
      set.add("c");
      set.add("d");
      set.add("e");
      EXPECTED.add(set);
   }

   /**
    * Test method for
    * {@link unqualifiedengineer.DinnerParties#find(java.util.List, int)}.
    */
   @Test
   public void testFind() {
      // it is impossible to choose groups of 1 from no people
      assertEquals(0, DinnerParties.find(friends, 1).size());

      // there is only one combination of zero people
      assertEquals(1, DinnerParties.find(friends, 0).size());

      friends.add("a");
      friends.add("b");
      friends.add("c");
      friends.add("d");
      friends.add("e");

      Set<Set<String>> actual = DinnerParties.find(friends, 3);
      assertEquals(EXPECTED.size(), actual.size());
      assertTrue(EXPECTED.equals(actual));
   }

}