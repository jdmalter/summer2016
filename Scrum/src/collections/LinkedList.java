package collections;

import java.util.NoSuchElementException;

import utilities.Predicate;

/**
 * Implements list interface with doubly linked nodes where each node refers to
 * the previous and next node in the list.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *        the type of element
 */
public class LinkedList<E> implements List<E> {

   /**
    * Doubly linked node which refers to the previous and next node in the list.
    * 
    * @author Jacob Malter
    *
    * @param <E>
    *        element type
    */
   private static final class Node<E> {

      /** element object */
      private E e;
      /** adjacent nodes in list */
      private Node<E> prev, next;

      /**
       * Constructs node with the given element.
       * 
       * @param e
       *        element object
       */
      private Node(E e) {
         this.e = e;
      }

   }

   /** string given to exception when set, get, or remove is invalid */
   private static final String INVALID_ACCESS_INDEX =
      "Invalid index for accessing element to list. "
         + "Index must be greater than zero and strictly less than size.";
   /** string given to exception when add is invalid */
   private static final String INVALID_ADD_INDEX = "Invalid index for adding"
      + " element to list. Index must be non-negative and less than or"
      + " equal to size.";

   /** the number of elements */
   private int size;
   /** ends of list comprised of doubly linked nodes */
   private Node<E> head, tail;

   @Override
   public boolean add(int i, E e) {
      if (i < 0 || i > size)
         throw new IndexOutOfBoundsException(INVALID_ADD_INDEX);

      // create node regardless of position
      Node<E> node = new Node<E>(e);

      if (size == 0) {
         // node in empty list
         head = node;
         tail = head;

      } else if (i == 0) {
         // first node in non-empty list
         node.next = head;
         head.prev = node;
         head = node;

      } else if (i == size) {
         // last node in non-empty list
         node.prev = tail;
         tail.next = node;
         tail = node;

      } else {
         // neither first or last node in non-empty list

         // find node at the given index
         Node<E> current = head;
         for (int n = 0; n < i; n++, current = current.next);

         // modify new node to reference adjacent nodes
         node.prev = current.prev;
         node.next = current;

         // modify adjacent nodes to reference new node
         node.prev.next = node;
         node.next.prev = node;
      }

      size++;
      return true;
   }

   @Override
   public E get(int i) {
      if (i < 0 || i >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      // optimize where the given index is the last node
      // this case is useful for stack and queue operations
      if (i == size - 1) return tail.e;

      // find node at the given index
      Node<E> current = head;
      for (int n = 0; n < i; n++, current = current.next);
      return current.e;
   }

   @Override
   public int indexOf(E e) {
      int n = 0;
      for (Node<E> current = head; current != null; current = current.next, n++)
         if (current.e == null ? e == null : current.e.equals(e)) return n;

      // the given element is not found
      return -1;
   }

   @Override
   public boolean remove(E e) {
      for (Node<E> current = head; current != null; current = current.next)
         if (current.e == null ? e == null : current.e.equals(e)) {

            // modify adjacent nodes to remove reference to node
            if (size == 1) {
               // only node in list
               head = null;
               tail = null;

            } else if (current.prev == null) {
               // first node in list
               head = head.next;
               head.prev = null;

            } else if (current.next == null) {
               // last node in list
               tail = tail.prev;
               tail.next = null;

            } else {
               // modify adjacent nodes to remove reference to node
               current.prev.next = current.next;
               current.next.prev = current.prev;
               current = null;
            }

            size--;
            return true;
         }

      return false;
   }

   @Override
   public E remove(int i) {
      if (i < 0 || i >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      // create result regardless of position
      E result = null;

      if (size == 1) {
         // only node in list
         result = head.e;
         head = null;
         tail = null;

      } else if (i == 0) {
         // first node in list
         result = head.e;
         head = head.next;
         head.prev = null;

      } else if (i == size - 1) {
         // last node in list
         result = tail.e;
         tail = tail.prev;
         tail.next = null;

      } else {
         // neither first or last node in list

         // find node at the given index and set result
         Node<E> current = head;
         for (int n = 0; n < i; n++, current = current.next);
         result = current.e;

         // modify adjacent nodes to remove reference to node
         current.prev.next = current.next;
         current.next.prev = current.prev;
         current = null;
      }

      size--;
      return result;
   }

   @Override
   public Iterator<E> reverseIterator() {
      return new Iterator<E>() {

         /** start iteration at the last element */
         private Node<E> cursor = tail;

         @Override
         public boolean hasNext() {
            return cursor != null;
         }

         @Override
         public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            E result = cursor.e;
            cursor = cursor.prev;
            return result;
         }

      };
   }

   @Override
   public Iterator<E> select(Predicate<? super E> p) {
      List<E> list = new LinkedList<E>();

      forEach(e -> {
         if (p.test(e)) list.add(e);
      });

      return list.iterator();
   }

   @Override
   public E set(int i, E e) {
      if (i < 0 || i >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      // find node at the given index and set result
      Node<E> current = head;
      for (int n = 0; n < i; n++, current = current.next);
      E result = current.e;
      current.e = e;

      return result;
   }

   @Override
   public List<E> sublist(int start, int end) {
      if (start < 0) throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      else if (start >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      else if (end >= size)
         throw new IndexOutOfBoundsException(INVALID_ACCESS_INDEX);

      else if (start > end) throw new IndexOutOfBoundsException(
         "start must be less than or equal to end");

      // create list regardless of position
      List<E> list = new LinkedList<E>();

      // start at front of list and look through nodes one by one until
      // index equals the given start position. after the given start position,
      // add each node to the new list until the given end position
      Node<E> current = head;
      for (int index = 0; index <= end; index++, current = current.next)
         if (start <= index) list.add(current.e);

      return list;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public Iterator<E> iterator() {
      return new Iterator<E>() {

         /** start iteration at the first element */
         private Node<E> cursor = head;

         @Override
         public boolean hasNext() {
            return cursor != null;
         }

         @Override
         public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            E result = cursor.e;
            cursor = cursor.next;
            return result;
         }

      };
   }

}