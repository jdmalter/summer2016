package model;

import collections.List;

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
public interface ProductBacklog<P extends ProductBacklogItem> extends List<P> {

}