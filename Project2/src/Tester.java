public class Tester{

    public static void main(String[] argS){
        MyBST tree = new MyBST();
//insertList test
        int[] list = new int[]{10, 45, 35, 36, 23, 5, 6, 1, 7, 9, 34};
        tree.insertList(list);
 
//----------------------------------------------------------------------------
// getAllValues() test
        System.out.println("\n" + tree.getAllValues());

//------------------------------------------------------------------------
//numberNodes test
        System.out.println("NodeCount: " + tree.numberNodes());
        
//-----------------------------------------------------------------------------
//removeLessThan() test
        System.out.println("removed: " + tree.removeLessThan(10));
        System.out.println( tree.getAllValues());
        System.out.println("NodeCount: " + tree.numberNodes());
        tree.removeLessThan(100);
//------------------------------------------------------------------------------
//numberOfLeaves test
        list = new int[]{10,1,8,5,9,45,35,36,23,15,13,18,17,20};
        tree.insertList(list);
        System.out.println(tree.getAllValues());
        System.out.println("Num leaves: " + tree.numberOfLeaves());
        
//-----------------------------------------------------------------------------
//number of full parents test
        System.out.println("full parents: " + tree.numberOfFullParents());
        
        System.out.println("Nodes at level 4: " + tree.howManyAtLevel(4));
        
        System.out.println("Number of nodes: " + tree.numberNodes());
        System.out.println("removed leaves: " + tree.removeAllLeaves());
        tree.removeLessThan(100);
        System.out.println("values: " + tree.getAllValues());
        tree.insert(1);
        tree.insert(2);
        System.out.println(tree.getAllValues());
        System.out.println(tree.removeAllLeaves());
        System.out.println(tree.getAllValues());
        System.out.println(tree.removeAllLeaves());
        System.out.println(tree.getAllValues());
    }

}