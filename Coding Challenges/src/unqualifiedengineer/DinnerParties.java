package unqualifiedengineer;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * "Given a table for n people, figure out every possible seating of the table
 * for all of your friends."
 * 
 * This implementation uses recursion. Although this function is definetly
 * O(2^n) because the result is a subset of the power set where the size of the
 * combination is r, or size, there are some optimizations for larger values of
 * r, or combination size, that eliminate extra recursion branches.
 * 
 * There are some details that help solve this problem with recursion.
 * <ol>
 * <li>Imagine a binary recursion tree. The left branch means exclude an
 * individual. The right branch means include an individual.</li>
 * <li>Each level in the binary recursion tree represents on individual being
 * considered to be included or excluded from a combination on every branch of
 * the recursion tree.</li>
 * <li>Only the branches of the recursion tree where the size of the group
 * equals the given size are accepting branches. Every other branch can be
 * eliminated from the possible results.</li>
 * </ol>
 * 
 * @see <href>https://youtu.be/3teHU4n-czU</href>
 * @author Jacob Malter
 *
 */
public final class DinnerParties {

	/**
	 * The size of the resulting set is n! / ( r! * (n - r)! ) where n is the
	 * size of friends and r is the given size or the quantity know as the
	 * binomial coefficient of n choose r. Returns set of combinations of
	 * non-repeating friends.
	 * 
	 * If the given list of friends is null, throws NullPointerException.
	 * 
	 * @param friends
	 *            list of objects
	 * @param size
	 *            maximum number of individuals in a combination of friends
	 * @return set of combinations of non-repeating friends
	 */
	public static <T> Set<Set<T>> find(List<T> friends, int size) {
		Objects.requireNonNull(friends, "friends must not be null");
		return combine(friends, size, new HashSet<Set<T>>(), new HashSet<T>(), 0);
	}

	/**
	 * Returns set of combinations of friends.
	 * 
	 * @param friends
	 *            list of objects
	 * @param size
	 *            maximum number of individuals in a combination of friends
	 * @param groups
	 *            set of combinations of non-repeating friends
	 * @param group
	 *            current combintation which may or may not include a new friend
	 * @param position
	 *            index to object in list of objects being included or excluded
	 * @return set of combinations of non-repeating friends
	 */
	private static <T> Set<Set<T>> combine(List<T> friends, int size, Set<Set<T>> groups, Set<T> group, int position) {
		if (group.size() == size) {

			// base case of recursion where the combination is complete
			groups.add(group);

		} else if (friends.size() - position < size - group.size()) {
			// friends.size() - position is the number of possible people who
			// could be added

			// size - group.size() is the number of people requried to accept a
			// combination of friends

			// if there are not enough possible people left for a combination to
			// be
			// accepted, we can ignore a combination before its recursion is
			// fully
			// complete

		} else {

			// on a recursion tree, this call would be a left branch where a
			// null
			// element is into the new group at the given position

			// leave out friend in new group
			combine(friends, size, groups, group, position + 1);

			// on a recursion tree, this call would be a right branch where a
			// new
			// element is added into the new group at the given position

			// include in friend in new group
			Set<T> newGroup = new HashSet<T>();
			newGroup.addAll(group);
			newGroup.add(friends.get(position));
			combine(friends, size, groups, newGroup, position + 1);
		}

		return groups;
	}

}