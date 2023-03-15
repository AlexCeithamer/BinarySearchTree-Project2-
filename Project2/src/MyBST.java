public class MyBST{

    private MyTreeNode root;

    public MyBST(){
        root = null;
    }
    
    /**
     * removes all leaf nodes (no children)
     * 
     * BIG-THETA = n
     * @return 
     */
    public int removeAllLeaves() {
        return removeAllLeaves(root, 0, root);
    }
    private int removeAllLeaves(MyTreeNode current, int count, MyTreeNode parent) {
        if (current != null) {
            if (current.getRight() == null && current.getLeft() == null) {
                ++count;
                if (root == current) {
                    root = null;
                } else if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
            count = removeAllLeaves(current.getLeft(), count, current);
            count = removeAllLeaves(current.getRight(), count, current);
            
        }
        return count;
    }
    
    /**
     * returns the number of nodes at a specified level passed in
     * 
     * BIG-THETA = n
     * @param level
     * @return 
     */
    public int howManyAtLevel(int level) {
        return howManyAtLevel(root, level, 0, 0);
    }
    private int howManyAtLevel(MyTreeNode current, int level, int count, int currLevel){
        if (current != null) {
            count = howManyAtLevel(current.getLeft(), level, count, currLevel + 1);
            count = howManyAtLevel(current.getRight(), level, count, currLevel + 1);
            if (currLevel == level) {
                ++count;
            }
        }
        return count;
    }
    
    /**
     * returns the number of parents that have 2 children
     * 
     * BIG-THETA = n
     * @return 
     */
    public int numberOfFullParents() {
        return numberOfFullParents(root, 0);
    }
    private int numberOfFullParents(MyTreeNode current, int count) {
        if (current != null) {
            count = numberOfFullParents(current.getLeft(), count);
            count = numberOfFullParents(current.getRight(), count);
            if (current.getLeft() != null && current.getRight() != null) {
                ++count;
            }
        }
        return count;
    }
    
    /**
     * Returns number of nodes without children (leaf)
     * 
     * BIG-THETA = n
     * @return 
     */
    public int numberOfLeaves() {
        return numberOfLeaves(root, 0);
    }
    private int numberOfLeaves(MyTreeNode current, int count) {
        if (current != null) {
            count = numberOfLeaves(current.getLeft(), count);
            count = numberOfLeaves(current.getRight(), count);
            if (current.getLeft() == null && current.getRight() == null) {
                ++count;
            }
        }
        return count;
    }
    
    
    /**
     * removes values less than the given value
     * 
     * BIG-THETA = n
     * @param value delete anything less than this value
     * @return number of nodes deleted
     */
    public int removeLessThan(int value) {
        return removeLessThan(root, value, 0);
    }
    private int removeLessThan(MyTreeNode current, int value, int count) {
        if (current != null) {
            count = removeLessThan(current.getLeft(), value, count);
            count = removeLessThan(current.getRight(), value, count);
            if (current.getElement() < value) {
                remove(root, current.getElement(), root);
                ++count;
            }
        }
        return count;
    }
    
    /**
     * Inserts elements in a list/array into the tree
     * 
     * BIG-THETA = nm (because insert is m and it has to run n times)
     * (without considering insert, the runtime is n
     * @param list 
     */
    public void insertList(int[] list) {
        for(int element: list) {
            insert(element);
        }
    }
    
    /**
     * Returns the number of nodes in our BST
     * 
     * BIG-THETA = n
     * @return 
     */
    public int numberNodes() {
        return numberNodes(root, 0);
    }
    private int numberNodes(MyTreeNode current, int count) {
        if (current != null) {
            count = numberNodes(current.getLeft(), count);
            ++count;
            count = numberNodes(current.getRight(), count);
        }
        return count;
        
    }
    
    /**
     * Gets all values and returns it as a string in ascending order.
     * 
     * BIG-THETA = n
     * @return 
     */
    public String getAllValues() {
        return getAllValues(root, "");
    }
    private String getAllValues(MyTreeNode current, String ascendingOrder) {
        if (current != null) {
            ascendingOrder = getAllValues(current.getLeft(), ascendingOrder);
            ascendingOrder += current.getElement() + " ";
            ascendingOrder = getAllValues( current.getRight(), ascendingOrder);
        }
        return ascendingOrder;
    }
    
    
    private void remove(MyTreeNode current, int element, MyTreeNode parent) {
        if (current == null) {
            throw new RuntimeException("Element not in tree");
        }
        //Recursion to find the node to be removed
        if (current.getElement() > element) {
            remove(current.getLeft(), element, current);
        } else if (current.getElement() < element) {
            remove(current.getRight(), element, current);
        } else {
            //check if it's a leaf
            if (current.getLeft() == null && current.getRight() == null) {
                if (root == current) {
                    root = null;
                } else if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            //check for one child
            } else if (current.getRight() != null && current.getLeft() == null) {
                if (current == root) {
                    root = current.getRight();
                } else {
                    if (parent.getLeft() == current) {
                        parent.setLeft(current.getRight());
                    } else {
                        parent.setRight(current.getRight());
                    }
                }
            } else if (current.getRight() == null && current.getLeft() != null) {
                if (current == root) {
                    root = current.getLeft();
                } else {
                    if (parent.getLeft() == current) {
                        parent.setLeft(current.getLeft());
                    } else {
                        parent.setRight(current.getLeft());
                    }
                }
            //Else, it's 2 children
            } else {
                MyTreeNode minParent = current;
                MyTreeNode min = current;
                if (current.getRight() != null) {
                    min = current.getRight();
                    while (min.getLeft() != null) {
                        minParent = min;
                        min = min.getLeft();
                    }
                }
                if (current != root) {
                    if (parent.getLeft() == current) {
                        parent.setLeft(min);
                    } else {
                        parent.setRight(min);
                    }
                } else {
                    root = min;
                }
                if (current != minParent) {
                    minParent.setLeft(min.getRight());
                    min.setRight(current.getRight());
                    min.setLeft(current.getLeft());
                } else {
                    if (current.getRight() == min) {
                        min.setLeft(current.getLeft());
                    } else {
                        min.setRight(current.getRight());
                    }
                }
                
                
            } //else statement for 2 children
        } //else statement after we found the element
    }
    

    private boolean contains(MyTreeNode current, int element){
        if(current==null){
            return false;
        } else if(current.getElement() == element){
            return true;
        } else if(element < current.getElement()){
            return contains(current.getLeft(), element);
        } else{
            return contains(current.getRight(), element);
        }
    }

    public boolean contains(int element){
        return contains(root, element);
    }

    private void print(MyTreeNode current){
        if(current!=null){
            print(current.getLeft());
            System.out.print(current.getElement()+" ");
            print(current.getRight());
        }
    }

    public void print(){
        print(root);
        System.out.println();
    }

    public void insert(int element){
        MyTreeNode temp = new MyTreeNode();
        temp.setElement(element);
        temp.setLeft(null);
        temp.setRight(null);
        if(root==null){
            root = temp;
        } else{
            //iterative way
            MyTreeNode current = root;
            while(current!=null){
                if(current.getElement() < element){
                    //go right
                    if(current.getRight()==null){                        
                        current.setRight(temp);
                        current = null;
                    } else{
                        current = current.getRight();
                    }
                } else{
                    //go left
                    if(current.getLeft()==null){
                        current.setLeft(temp);
                        current = null;
                    } else{
                        current = current.getLeft();
                    }
                }
            }
        }
    }

}