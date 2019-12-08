import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of a Set based on a binary search tree.
 *
 * @param <T> the type of elements maintained by this set
 */
public class BSTSet<T extends Object & Comparable<? super T>> implements Set<T> {
    /**
     * This class represents a binary search tree
     * (or equivalently, a node of such a tree).
     */
    class BSTree {
        T value;
        BSTree left, right;

        BSTree(T value) {
            this.value = value;
        }

        /**
         * Add the given element to the tree.
         *
         * @param element the element to add
         * @return true if the element was successfully added;
         * false if the element was already contained in the tree
         */
        boolean add(T element) {
            if (element == null) return false;
            BSTree newNode = new BSTree(element);
            return this.add(newNode);
        }

        /**
         * Add the given subtree to this tree.
         *
         * @param subtree a binary search tree (or subtree) to add to this tree
         * @return true if the tree was successfully added; false if the value
         * at the root node of 'subtree' was already contained in the tree
         */
        boolean add(BSTree subtree) {
            if (subtree == null) return false;
            if (value.compareTo(subtree.value) < 0) {
                // right-hand tree
                if (right != null) {
                    return right.add(subtree);
                } else {
                    right = subtree;
                    return true;
                }
            } else if (value.compareTo(subtree.value) > 0) {
                // left-hand tree
                if (left != null) {
                    return left.add(subtree);
                } else {
                    left = subtree;
                    return true;
                }
            } else {
                // same value
                return false;
            }
        }

        /**
         * Find the given element in the tree, and return the tree that is
         * rooted at the node containing the element.
         * If the element is found and 'remove' is true, remove the node
         * containing the element (and its subtrees) from the tree.
         *
         * @param element the element to find
         * @param remove  if true, remove the node containing the element from the tree
         * @return the node that contains the element, or null if the element is not in the tree
         */
        BSTree find(T element, boolean remove) {
            if (element == null) return null;
            if (value.compareTo(element) < 0) {
                // right-hand tree
                if (right != null) {
                    BSTree found = right.find(element, remove);
                    if (found != null && found.value.equals(element) && remove) {
                        right = null;
                    }
                    return found;
                } else {
                    return null;
                }
            } else if (value.compareTo(element) > 0) {
                // left-hand tree
                if (left != null) {
                    BSTree found = left.find(element, remove);
                    if (found != null && found.value.equals(element) && remove) {
                        left = null;
                    }
                    return found;
                } else {
                    return null;
                }
            } else {
                // same value
                return this;
            }
        }

        /**
         * Add the elements in this tree to the given list of elements,
         * in the order in which they are sorted in the tree.
         *
         * @param elements the list of elements to which the elements in this
         *                 tree are to be added
         */
        void addElementsToList(List<T> elements) {
            if (left != null) left.addElementsToList(elements);
            elements.add(this.value);
            if (right != null) right.addElementsToList(elements);
        }
    }

    /**
     * The root node of the tree containing all elements of the set.
     */
    private BSTree root;
    private int numElements = 0;

    @Override
    public boolean add(T element) {
        BSTree newNode = new BSTree(element);
        if (root == null) {
            root = newNode;
            numElements++;
            return true;
        } else {
            boolean added = root.add(element);
            if (added) numElements++;
            return added;
        }
    }

    @Override
    public boolean remove(T element) {
        if (root != null) {
            if (root.value.equals(element)) {
                if (root.left != null) {
                    BSTree right = root.right;
                    root = root.left;
                    root.add(right);
                } else if (root.right != null) {
                    root = root.right;
                } else {
                    root = null;
                }
                numElements--;
                return true;
            } else {
                BSTree node = root.find(element, true);
                if (node != null) {
                    root.add(node.left);
                    root.add(node.right);
                }
                boolean removed = (node != null);
                if (removed) numElements--;
                return removed;
            }
        }

        return false;
    }

    @Override
    public boolean contains(T element) {
        if (root != null) {
            return root.find(element, false) != null;
        }
        return false;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public Object[] toArray() {
        List<T> elements = new ArrayList<>();
        if (root != null) {
            root.addElementsToList(elements);
        }
        return elements.toArray();
    }

    @Override
    public Set<T> newInstance() {
        return new BSTSet<>();
    }
}
