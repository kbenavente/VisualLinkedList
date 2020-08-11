public class Node<S extends Comparable<S>, U extends Comparable<U>, V extends Comparable<V>> {

    protected S category1;
    protected U category2;
    protected V category3;

    protected Node<S, U, V> right;
    protected Node<S, U, V> left;
    protected Node<S, U, V> down;

    // Constructor

    /** Constructor that accepts three elements where each is set the specified category.
     *
     * @param category1
     *        Element for category1.
     * @param category2
     *        Element for category2.
     * @param category3
     *        Element for category3.
     */

    public Node(S category1, U category2, V category3) {

        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;

        this.right = null;
        this.left = null;
        this.down = null;

    }

    // Getters

    /** Getter for {@code category1}
     *
     * @return Element in category1.
     */

    public S getCategory1() {

        return category1;

    }

    /** Getter for {@code category2}
     *
     * @return Element in category2.
     */

    public U getCategory2() {

        return category2;

    }

    /** Getter for {@code category3}
     *
     * @return Element in category3.
     */

    public V getCategory3() {

        return category3;

    }

    // Setters

    /**
     *  Setter for {@code category1}
     *
     * @param category1
     *        Element to set category1.
     */

    public void setCategory1(S category1) {

        this.category1 = category1;

    }

    /**
     *  Setter for {@code category2}
     *
     *  @param category2
     *         Element to set category2.
     */

    public void setCategory2(U category2) {

        this.category2 = category2;

    }

    /**
     *  Setter for {@code category3}
     *
     * @param category3
     *        Element to set category3.
     */

    public void setCategory3(V category3) {

        this.category3 = category3;

    }

}
