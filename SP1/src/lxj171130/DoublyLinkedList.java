/**
 * @author Terrence Park
 * @author Leejia James
 *
 * Doubly linked list using inheritance from the instructor-provided
 * singly linked list.
 *
 * Ver 1.0: 2018/08/24
 */
package lxj171130;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

    /**
     * Doubly linked list node holding the element and next/previous pointers.
     *
     * @param <E> element type
     */
    static class Entry<E> extends SinglyLinkedList.Entry<E> {

        /**
         * Pointer to previous entry.
         */
        Entry<E> prev;

        /**
         * Generate new entry.
         *
         * @param x element
         * @param next next entry
         * @param prev previous entry
         */
        Entry(E x, Entry<E> next, Entry<E> prev) {
            super(x, next);
            this.prev = prev;
        }
    }

    /**
     * Creates new doubly linked list.
     */
    public DoublyLinkedList() {
        // Use the DLL entry instead of SLL entry
        head = new Entry<>(null, null, null);
        tail = head;
        size = 0;
    }

    /**
     * Gets the doubly linked list iterator.
     *
     * @return doubly linked list iterator
     */
    public DLLIterator dllIterator() {
        return new DLLIterator();
    }

    /**
     * Iterator to iterate over this doubly linked list.
     */
    public class DLLIterator extends SinglyLinkedList<T>.SLLIterator {

    	/**
         * @author Leejia James
         *
    	 * Checks if there is a previous element.
         *
    	 * @return true if there is a previous element
    	 */
        public boolean hasPrev() {
            Entry<T> dllCursor = (Entry<T>)cursor;
        	return dllCursor.prev != null && dllCursor.prev != head;
        }

        /**
         * @author Leejia James
         *
         * Gets the previous element from where the cursor is. Ex: for the
         * list 1 2 3, if the cursor is on 2, then prev() returns 1 and
         * moves the cursor to 1.
         *
         * @return the previous element
         */
        public T prev() {
        	// checks if we are at the head of the list.
        	if (((Entry<T>)cursor).prev == head) {
        		throw new NoSuchElementException();
        	}
			cursor = ((Entry<T>)cursor).prev;
			prev = ((Entry<T>)cursor).prev;
			ready = true;
			return cursor.element;
        }

        /**
         * @author Terrence Park
         *
         * Inserts an element right after the cursor (before the element that will
         * be returned by a call to next()).
         *
         * @param x element to add
         */
        public void add(T x) {
            // Create entry to add to the list
            Entry<T> toAdd = new Entry<>(x, (Entry<T>)cursor.next, (Entry<T>)cursor);

            if (cursor.next != null) { // Edge case where cursor is at end of list
                ((Entry<T>) cursor.next).prev = toAdd;
            }
            cursor.next = toAdd;
            size++;
        }

        /**
         * @author Terrence Park
         * @author Leejia James
         *
         * Removes the current element (retrieved by the most recent next() or
         * prev()). Remove can be called only if next has been called and the element
         * has not been removed.
         */
        @Override
        public void remove()
        {
            // First remove from the list
            super.remove();

            // Check if we are at end of list
            if (cursor.next != null) {
                // Fix the prev pointer of the element after the removed element
                ((Entry<T>)cursor.next).prev = (Entry<T>)cursor;
            }
        }
    }

    /**
     * @author Terrence Park
     *
     * Adds an element x to the end of the list.
     *
     * @param x element to add
     */
    @Override
    public void add(T x) {
        // Use the DLL entry instead of SLL entry
        super.add(new Entry<>(x, null, (Entry<T>)tail));
    }

    /**
     * @author Terrence Park
     *
     * Rearrange the elements of the list by linking the elements at even index
     * followed by the elements at odd index. Implemented by rearranging pointers
     * of existing elements without allocating any new elements.
     */
    @Override
    public void unzip() {
        if(size < 3) {  // Too few elements.  No change.
            return;
        }

        // First unzip to adjust the next pointers
        super.unzip();

        // Since the next pointers are set properly, we can iterate over them
        // to fix the prev pointers
        Iterator<T> it = iterator();
        Entry<T> prev = (Entry<T>)head;
        Entry<T> curr = (Entry<T>)head.next;
        while (curr != null) {
            curr.prev = prev;
            prev = curr;
            curr = (Entry<T>)curr.next;
        }
    }
}
