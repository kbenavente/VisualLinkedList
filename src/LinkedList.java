public class LinkedList<S extends Comparable<S>, U extends Comparable<U>, V extends Comparable<V>> {

    private Node<S, U, V> head;

    private String category1Label;
    private String category2Label;
    private String category3Label;

    private int groupingCategory;

    private int size;

    /**
     * Default constructor for {@code LinkedList<S, U, V>}  object.
     */

    public LinkedList() {

        this.head = null;
        this.groupingCategory = 1;
        this.category1Label = "First";
        this.category2Label = "Second";
        this.category3Label = "Third";
        this.size = 0;

    }

    /** Constructor that creates an instance of a {@code LinkedList<S, U, V>} object with custom grouping category and category labels.
     *
     * @param groupingCategory
     *        integer representing the category to group by, 1 - 3 (inclusive).
     * @param category1Label
     *        {@code String} representing the name of category 1.
     * @param category2Label
     *        {@code String} representing the name of category 2.
     * @param category3Label
     *        {@code String} representing the name of category 3.
     *
     * @throws IndexOutOfBoundsException
     *         If category number is not 1 - 3 (inclusive).
     */

    public LinkedList(int groupingCategory, String category1Label, String category2Label, String category3Label) {

        if(groupingCategory < 1 || groupingCategory > 3)
            throw new IndexOutOfBoundsException("Invalid Grouping category value of " + groupingCategory);

        this.head = null;
        this.groupingCategory = groupingCategory;
        this.category1Label = category1Label;
        this.category2Label = category2Label;
        this.category3Label = category3Label;
        this.size = 0;

    }

    // Methods

    /** Creates a new parameterized data type, {@code Node<S, U, V>}, and  adds it to the end of the linked list data structure.
     *
     * @param value1
     *        A generic data type which implements the {@code Comparable<E>} interface. {@code value1} will be
     *        {@code category1} of the parameterized data type, {@code Node<S, U, V>}.
     * @param value2
     *        A generic data type which implements the {@code Comparable<E>} interface. {@code value1} will be
     *        {@code category2} of the parameterized data type, {@code Node<S, U, V>}.
     * @param value3
     *        A generic data type which implements the {@code Comparable<E>} interface. {@code value1} will be
     *        {@code category3} of the parameterized data type, {@code Node<S, U, V>}.
     */

    public void add(S value1, U value2, V value3) {

        if(this.isEmpty()) {

            this.head = new Node<>(value1, value2, value3);
            this.size++;

        } else {

            boolean groupNotFound = true;
            Node<S, U, V> currentNode = this.head;
            Node<S, U, V> previousNode = this. head;

            while(currentNode != null && groupNotFound) {

                previousNode = currentNode;

                switch(this.groupingCategory) {

                    case 1:
                        if(currentNode.getCategory1().compareTo(value1) == 0)
                            groupNotFound = false;

                        break;

                    case 2:
                        if(currentNode.getCategory2().compareTo(value2) == 0)
                            groupNotFound = false;

                        break;

                    case 3:
                        if(currentNode.getCategory3().compareTo(value3) == 0)
                            groupNotFound = false;

                        break;

                }

                currentNode = currentNode.right;

            }

            if(!groupNotFound) {

                Node<S, U, V> currentColumnNode = previousNode;

                while(currentColumnNode.down != null)
                    currentColumnNode = currentColumnNode.down;

                currentColumnNode.down = new Node<>(value1, value2, value3);
                this.size++;


            } else {

                previousNode.right = new Node<>(value1, value2, value3);
                previousNode.right.left = previousNode;
                this.size++;

            }

        }

    }

    /**
     * Clears the entire LinkedList data structure of any data.
     */

    public void clear() {

        this.head = null;
        this.size = 0;

    }

    /** Deletes the first {@code Node<S, U, V>} in the main list from the {@code LinkedList<S, U, V} data structure.
     *
     * @throws EmptyListException
     *         If the {@code LinkedList<S, U, V} data structure holds no data. i.e. has no data/is empty.
     */

    public void deleteFirst() {

        if(this.size == 0) {

            throw new EmptyListException("deleteFirst");

        } else if(this.size == 1) {

            this.clear();

        } else {

            if(this.head.down != null) {

                this.head.down.right = this.head.right;
                this.head.right = this.head.down;
                this.head = this.head.down;

                this.size--;

            } else {

                this.head = this.head.right;
                this.head.left = null;

                this.size--;

            }

        }

    }

    /** Deletes the last {@code Node<S, U, V>} in the main list from the {@code LinkedList<S, U, V} data structure.
     *
     * @throws EmptyListException
     *         If the {@code LinkedList<S, U, V} data structure holds no data. i.e. has no data/is empty.
     */

    public void deleteLast() {

        if(this.size == 0) {

            throw new EmptyListException("deleteLast");

        } else if(this.head.right == null) {

            this.deleteFirst();

        } else {

            Node<S, U, V> currentMainListNode = this.head;

            while(currentMainListNode.right.right != null)
                currentMainListNode = currentMainListNode.right;

            if(currentMainListNode.right.down != null) {

                currentMainListNode.right = currentMainListNode.right.down;
                currentMainListNode.right.left = currentMainListNode;
                this.size--;

            } else {

                currentMainListNode.right = null;
                this.size--;

            }

        }

    }

    /** Deletes the {@code Node<S, U, V>} at the specified {@code mainIndex} and {@code subIndex} in the {@code LinkedList<S, U, V>}.
     *
     *
     * @param mainIndex
     *        Column/Main Index of the {@code Node<S, U, V>} that is to be deleted.
     *        
     * @param subIndex
     *        Row/Sub Index of the {@code Node<S, U, V>} that is to be deleted.
     *
     * @throws IndexOutOfBoundsException
     *         If {@code LinkedList<S, U, V>} data structure is empty, {@code mainIndex} is negative/exceeds actual main index, or
     *         {@code subIndex} is negative/exceeds actual sub index.
     */

    public void delete(int mainIndex, int subIndex) {

        if(this.isEmpty() || mainIndex < 0 || subIndex < 0) {

            throw new IndexOutOfBoundsException("Out of bounds main index or sub index" + mainIndex + " " +subIndex);

        } else if(mainIndex == 0 && subIndex == 0) {

            this.deleteFirst();

        } else {

            Node<S, U, V> currentNode = this.head;
            int rowCount = 0;
            int columnCount = 0;

            while(columnCount < mainIndex) { // Finds the Node at the main index

                currentNode = currentNode.right;
                columnCount++;

                if(currentNode == null)
                    throw new IndexOutOfBoundsException("" + mainIndex);

            }

            if(subIndex == 0) { // In the case we don't go into a sublist

                if(currentNode.down != null) { // In the case there's sublist

                    if(currentNode.right != null) { // In the case there's a Node to the right

                        currentNode.down.right = currentNode.right;
                        currentNode.left.right = currentNode.down;
                        currentNode.down.right.left = currentNode.down;
                        currentNode.down.left = currentNode.left;
                        this.size--;

                    } else { // In the case there's no right Node

                        currentNode.left.right = currentNode.down;
                        currentNode.down.left = currentNode.left;
                        this.size--;

                    }

                } else { // In the case there's no sublist

                    if(currentNode.right != null) { // In the case there's a Node to the right

                        currentNode.left.right = currentNode.right;
                        currentNode.right.left = currentNode.left;
                        this.size--;

                    } else { // In the case there's no right Node

                        currentNode.left.right = null;
                        this.size--;

                    }

                }

            } else { // If we need to go through a sublist

                Node<S, U, V> previousNode = currentNode;

                while (rowCount < subIndex) {

                    previousNode = currentNode;

                    if (currentNode.down == null)
                        throw new IndexOutOfBoundsException("" + subIndex);

                    currentNode = currentNode.down;
                    rowCount++;

                }

                if(previousNode.down.down != null) {

                    previousNode.down = previousNode.down.down;
                    this.size--;

                } else {

                    previousNode.down = null;
                    this.size--;

                }

            }

        }

    }

    /** Returns a {@code String} representation of the {@code Node<S, U, V>} object's specified category value.
     *
     * @param mainIndex
     *        main index of the {@code Node<S, U, V>}.
     * @param category
     *        integer number of category that is to be retrieved.
     * @return {@code String} representation of the {@code Node<S, U, V>} object's specified category value.
     *
     * @throws IndexOutOfBoundsException
     *         If category number is not 1 - 3 (inclusive).
     */

    public String get(int mainIndex, int category) {

        if(category < 0 || category > 3) {

            throw new IndexOutOfBoundsException("Invalid Grouping category value of " + category);

        } else if(mainIndex < 0) {

            throw new IndexOutOfBoundsException("" + mainIndex);

        } else {

            int columnIndexCounter = 0;
            Node<S, U, V> currentNode = this.head;

            while(columnIndexCounter < mainIndex) {

                currentNode = currentNode.right;
                columnIndexCounter++;

                if(currentNode == null)
                    throw new IndexOutOfBoundsException("" + mainIndex);

            }

            switch(category) {

                case 1: return "" + currentNode.getCategory1();
                case 2: return "" + currentNode.getCategory2();
                case 3: return "" + currentNode.getCategory3();
                default: return "BLANK";

            }

        }

    }

    /** Returns a {@code String} representation of the {@code Node<S, U, V>} object's specified category value.
     *
     * @param mainIndex
     *        main index of the {@code Node<S, U, V>}.
     * @param subIndex
     *        sub index of the {@code Node<S, U, V>}.
     * @param category
     *        integer number of category that is to be retrieved.
     * @return {@code String} representation of the {@code Node<S, U, V>} object's specified category value.
     *
     * @throws IndexOutOfBoundsException
     *         If category number is not 1 - 3 (inclusive).
     */

    public String get(int mainIndex, int subIndex, int category) { // NullPointerException java: 349

        if(category < 0 || category > 3) {

            throw new IndexOutOfBoundsException("Invalid Grouping category value of " + category);

        } else {

            int columnIndexCounter = 0;
            int rowIndexCounter = 0;
            Node<S, U, V> currentNode = this.head;

            while(columnIndexCounter < mainIndex) {

                if(currentNode == null)
                    throw new IndexOutOfBoundsException("" + mainIndex);

                currentNode = currentNode.right;
                columnIndexCounter++;

            }

            while(rowIndexCounter < subIndex) {

                currentNode = currentNode.down;
                rowIndexCounter++;

                if(currentNode == null)
                    throw new IndexOutOfBoundsException("" + subIndex);

            }

            if(currentNode == null)
                throw new IndexOutOfBoundsException("" + subIndex);

            switch(category) {

                case 1: return "" + currentNode.getCategory1();
                case 2: return "" + currentNode.getCategory2();
                case 3: return "" + currentNode.getCategory3();
                default: return "BLANK";

            }

        }

    }

    /** Regroups the current {@code LinkedList<S, U, V>} object according to the {@code int groupingCategory}
     *  provided.
     *
     * @param groupingCategory
     *        the category number in which the current list is to be regrouped.
     *
     * @throws IndexOutOfBoundsException
     *         If category number is not 1 - 3 (inclusive).
     */

    public void regroup(int groupingCategory) {

        if (groupingCategory < 1 || groupingCategory > 3) {

            throw new IndexOutOfBoundsException("Invalid Grouping category value of " + groupingCategory);

        } else {

            LinkedList<S, U, V> newLinkedList = new LinkedList<>(groupingCategory, this.category1Label, this.category2Label, this.category3Label);

            Node<S, U, V> currentRowNode = this.head;

            while (currentRowNode != null) {

                Node<S, U, V> currentColumnNode = currentRowNode;

                while (currentColumnNode != null) {

                    newLinkedList.add(currentColumnNode.getCategory1(), currentColumnNode.getCategory2(), currentColumnNode.getCategory3());

                    currentColumnNode = currentColumnNode.down;

                }

                currentRowNode = currentRowNode.right;

            }

            this.head = newLinkedList.head;
            this.size = newLinkedList.size;

        }

    }

    /**
     * @return {@code LinkedList<S, U, V>} object's number of {@code Node<S, U, V>} objects.
     */

    public int size() {

        return this.size;

    }

    /**
     * @return {@code true} if {@code LinkedList<S, U, V>} data structure is empty and {@code false} if it is populated.
     */

    public boolean isEmpty() {

        return this.size == 0;

    }

    /**
     *
     * @return A {@code String} representation of the {@code LinkedList<S, U, V>} object.
     */

    public String toString() {

        String result = "******************************************\n\n";

        Node<S, U, V> currentColumnNode = this.head;

        while(currentColumnNode != null) {

            Node<S, U, V> curentRowNode = currentColumnNode;

            while(curentRowNode != null) {

                result += "[" + category1Label + ": " + curentRowNode.getCategory1() + "\n";
                result += " " + category2Label + ": " + curentRowNode.getCategory2() + "\n";
                result += " " + category3Label + ": " + curentRowNode.getCategory3() + "]\n\n";

                curentRowNode = curentRowNode.down;

            }

            result += "******************************************\n\n";

            currentColumnNode = currentColumnNode.right;

        }

        return result;

    }

    /**
     *  Setter for {@code category1Label}
     *
     *  @param category1Label
     *         Element to set category1Label.
     */

    public void setCategory1Label(String category1Label) {

        this.category1Label = category1Label;

    }

    /**
     *  Setter for {@code category2Label}
     *
     *  @param category2Label
     *         Element to set category2Label.
     */

    public void setCategory2Label(String category2Label) {

        this.category2Label = category2Label;

    }

    /**
     *  Setter for {@code category3Label}
     *
     *  @param category3Label
     *         Element to set category3Label.
     */

    public void setCategory3Label(String category3Label) {

        this.category3Label = category3Label;

    }

}
