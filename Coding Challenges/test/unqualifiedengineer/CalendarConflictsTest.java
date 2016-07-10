package unqualifiedengineer;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import unqualifiedengineer.CalendarConflicts.Event;

/**
 * Tests {@link unqualifiedengineer.CalendarConflicts} class.
 * 
 * @author Jacob Malter
 *
 */
public class CalendarConflictsTest {

	/** input array of event used to create CalendarConflicts */
	private Event[] calendar = new Event[] { new Event("a", 1, 2), new Event("b", 3, 5), new Event("c", 4, 6),
			new Event("d", 7, 10), new Event("e", 8, 11), new Event("f", 10, 12), new Event("g", 13, 14),
			new Event("h", 13, 14) };
	/** list of expected results from findConflicts for one case */
	private static final List<String> EXPECTED = new LinkedList<String>();

	static {
		// set up expected results
		EXPECTED.add("b");
		EXPECTED.add("c");

		EXPECTED.add("d");
		EXPECTED.add("e");
		EXPECTED.add("f");

		EXPECTED.add("g");
		EXPECTED.add("h");
	}

	/**
	 * Test method for correctness for
	 * {@link unqualifiedengineer.CalendarConflicts#findConflicts()}.
	 */
	@Test
	public void testFindConflicts() {
		assertEquals(0, new CalendarConflicts(new Event[] {}).findConflicts().size());

		assertEquals(0, new CalendarConflicts(new Event[] { new Event("placeholder", 0, 1) }).findConflicts().size());

		equals(EXPECTED, new CalendarConflicts(calendar).findConflicts());
	}

	/**
	 * Compares the two given lists of equality of length and elements.
	 * 
	 * @param expected
	 *            what the given list should be equal to
	 * @param actual
	 *            what the given list actually is
	 */
	private static void equals(List<String> expected, List<String> actual) {
		assertEquals(expected.size(), actual.size());
		for (Iterator<String> eIterator = expected.iterator(), aIterator = actual.iterator(); eIterator.hasNext();) {
			assertEquals(eIterator.next(), aIterator.next());
		}
	}

}