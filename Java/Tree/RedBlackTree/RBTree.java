import java.util.ArrayList;
import java.util.List;


public class RBTree<T extends Comparable<T>> {
    public Node<T> root; // The root node of the tree


    // Check property 1
    private boolean leafChildRecurse(Node<T> cur) {
        if (cur.value != null) {
            return leafChildRecurse(cur.left) & leafChildRecurse(cur.right);
        } else {
            if (cur.colour == Colour.BLACK) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean testProp1() {
        if (root != null && root.colour != Colour.BLACK) 
            return false;

        return leafChildRecurse(root);
    }

    // Check property 2
    private boolean childRecurseForParentColour(Node<T> cur) {
        if (cur.value != null) {
            if (cur.colour == Colour.RED) {
                if (cur.parent == null || cur.parent.colour != Colour.BLACK) {
                    return false;
                }
            }

            return childRecurseForParentColour(cur.left) & childRecurseForParentColour(cur.right);

        } else {
            return true;
        }
    }

    public boolean testProp2() {
        return childRecurseForParentColour(root);
    }
    
     // Check property 3
    private int blackHeightRecurse(Node cur) {
        // Leaf node returns 1.
        if (cur.value == null) return 1;

        int leftHeight, rightHeight, curCnt;

        leftHeight = blackHeightRecurse(cur.left);
        rightHeight = blackHeightRecurse(cur.right);

        if (cur.colour == Colour.RED) curCnt = 0;
        else curCnt = 1;

        if (leftHeight == -1 || rightHeight == -1 || leftHeight != rightHeight) {
            return -1;
        } else {
            return leftHeight + curCnt;
        }
    }

    public boolean testProp3() {
        int height;

        height = blackHeightRecurse(root);
        if (height == -1) {
            return false;
        } else {
            return true;
        }
    }

    /** 
     * Base class for node with link to the parent
     *
     * @param <T> data type
     */
    public class Node<T extends Comparable<T>> {
        Colour colour;       // Node colour
        T value;             // Node value
        Node<T> parent;      // Parent node
        Node<T> left, right;        // Children nodes
        
        public Node(T value) {
            this.value = value;
            this.colour = Colour.BLACK;
            this.parent = null;

            // Initialise children leaf nodes
            this.left = new Node<T>();
            this.right = new Node<T>();
            this.left.parent = this;
            this.right.parent = this;
        }

        // Leaf node
        public Node() {
            this.value = null;
            this.colour = Colour.BLACK;
        }

        public void setColour(boolean red) {
        	this.colour = red ? Colour.RED : Colour.BLACK;
        }
    }

    /**
     *  Initialize empty RBTree
     */
    public RBTree() {
        root = null;
    }

    /**
     * Add a new node into the tree with {@code root} node.
     * @param root Node<T> The root node of the tree where x is being inserted.
     * @param x Node<T> New node being inserted. 
     */
    private void insertRecurse(Node<T> root, Node<T> x){
            if (root.value.compareTo(x.value) > 0) {
                if (root.left.value == null) {
                    root.left = x;
                    x.parent = root;
                } else {
                    insertRecurse(root.left, x);
                }
            } else if (root.value.compareTo(x.value) < 0) {
                if (root.right.value == null) {
                    root.right = x;
                    x.parent = root;
                } else {
                    insertRecurse(root.right, x);
                }
            }
            // Do nothing if the tree already has a node with the same value.
    }

    /**
     * Insert node into RBTree. 
     * 
     * @param x Node<T> The new node being inserted into the tree.
     */
    private void insert(Node<T> x) {
        // Insert node into tree
        if (root == null) {
            root = x;
        } else {
            insertRecurse(root, x);
        }

        // Fix tree
        while (x.value != root.value && x.parent.colour == Colour.RED) {
            boolean left = x.parent == x.parent.parent.l; // Is parent a left node
            Node<T> uncle = left ? x.parent.parent.r : x.parent.parent.l; // Get opposite "uncle" node to parent

            if (uncle.colour == Colour.RED) {
                // Case 1 / 4: Recolour
                x.parent.colour = Colour.BLACK;
                uncle.colour = Colour.BLACK;
                uncle.parent.colour = Colour.RED;

                // Check if violated further up the tree
                x = x.parent.parent;
            } else {
                if (x.value == (left ? x.parent.r.value : x.parent.l.value)) {
                    // Case 2 / 5 : Left (uncle is left node) / Right (uncle is right node) Rotation
                    x = x.parent;
                    if (left) {
                        // Perform left rotation
                        if (x.value == root.value) root = x.r; // Update root
                        rotateLeft(x);
                    } else {
                        // This is part of the "then" clause where left and right are swapped
                        // Perform right rotation
                        if (x.value == root.value) {
                            root = x.l;
                        }
                        rotateRight(x);
                    }
                }
                // Adjust colours to ensure correctness after rotation
                x.parent.colour = Colour.BLACK;
                x.parent.parent.colour = Colour.RED;

                // Case 3 / 6 : Right (uncle is left node) / Left (uncle is right node) Rotation
                if (left) {
                    // Perform right rotation
                    if (x.parent.parent.value == root.value) {
                        root = x.parent;
                    }
                    rotateRight(x.parent.parent);
                } else {
                    // This is part of the "then" clause where left and right are swapped
                    // Perform left rotation
                    if (x.parent.parent.value == root.value) {
                        root = x.parent;
                    }
                    rotateLeft(x.parent.parent);
                }
            }
        }

        // Ensure property 2 (root and leaves are black) holds
        root.colour = Colour.BLACK;

    }
    
    /**
     * (Safely) insert a value into the tree
     * @param value T The value of the new node being inserted.
     */
    public void insert(T value) {
        Node<T> node = new Node<T>(value);
        if (node != null)
            insert(node);
    }

    /** Rotate the node so it becomes the child of its right branch
    /*
        e.g.
              [x]                    b
             /   \                 /   \
           a       b     == >   [x]     f
          / \     / \           /  \
         c  d    e   f         a    e
                              / \
                             c   d
    */
    public void rotateLeft(Node<T> x) {
        // Make parent (if it exists) and right branch point to each other
        if (x.parent != null) {
            // Determine whether this node is the left or right child of its parent
            if (x.parent.l.value == x.value) {
                x.parent.l = x.r;
            } else {
                x.parent.r = x.r;
            }
        }
        x.r.parent = x.parent;

        x.parent = x.r;
        // Take right node's left branch
        x.r = x.parent.l;
        x.r.parent = x;
        // Take the place of the right node's left branch
        x.parent.l = x;
    }

    /** Rotate the node so it becomes the child of its left branch
    /*
        e.g.
              [x]                    a
             /   \                 /   \
           a       b     == >     c     [x]
          / \     / \                   /  \
         c  d    e   f                 d    b
                                           / \
                                          e   f
    */
    public void rotateRight(Node<T> x) {
        // Make parent (if it exists) and right branch point to each other
        if (x.parent != null) {
            // Determine whether this node is the left or left child of its parent
            if (x.parent.l.value == x.value) {
                x.parent.l = x.l;
            } else {
                x.parent.r = x.l;
            }
        }
        x.l.parent = x.parent;

        x.parent = x.l;
        // Take left node's right branch
        x.l = x.parent.r;
        x.l.parent = x;
        // Take the place of the right node's left branch
        x.parent.r = x;
    }

    /**
     *  Return the result of a pre-order traversal of the tree
     * @param tree Tree we want to pre-order traverse
     * @return pre-order traversed tree
     */
    private String preOrder(Node<T> tree) {
        if(tree != null && tree.value != null) {
            String leftStr = preOrder(tree.l);
            String rightStr = preOrder(tree.r);
            return tree.value + (leftStr.isEmpty() ? leftStr : " " + leftStr) + (rightStr.isEmpty() ? rightStr : " " + rightStr);
        }
        
        return "";
    }

    public String preOrder() {
        return preOrder(root);
    }

    /**
     *  Return the corresponding node of a value, if it exists in the tree
     * @param x Node<T> The root node of the tree we search for the value {@code v}
     * @param v Node<T> The node that we are looking for
     * @return
     */
    private Node<T> find(Node<T> x, T v) {
        if (x.value == null)
            return null;

        int cmp = v.compareTo(x.value);
        if (cmp < 0)
            return find(x.left, v);
        else if (cmp > 0)
            return find(x.right, v);
        else
            return x;
    }

    /**
     * Returns a node if the value of the node is {@code key}.
     * 
     * @param key T The value we are looking for
     * @return
     */
    public Node<T> search(T key) {
        return find(root, key);
    }

    public enum Colour {
        RED,
        BLACK;
    }

}
