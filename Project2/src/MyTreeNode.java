public class MyTreeNode{

    private int element;
    private MyTreeNode left;
    private MyTreeNode right;

    public int getElement(){
        return element;
    }

    public void setElement(int element){
        this.element = element;
    }

    public void setRight(MyTreeNode right){
        this.right = right;
    }

    public void setLeft(MyTreeNode left){
        this.left = left;
    }

    public MyTreeNode getLeft(){
        return left;
    }

    public MyTreeNode getRight(){
        return right;
    }

}