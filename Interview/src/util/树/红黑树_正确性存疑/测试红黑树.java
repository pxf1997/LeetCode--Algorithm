package util.树.红黑树_正确性存疑;

import org.junit.Test;

/**
 * @author pxf
 * @create 2021-07-01 15:00
 */
public class 测试红黑树 {
    public static void main(String[] args) {

    }

    @Test
    public void test1() {
        RBTree<String> bst = new RBTree<String>();
        bst.addNode("d");
        bst.addNode("d");
        bst.addNode("c");
        bst.addNode("c");
        bst.addNode("b");
        bst.addNode("f");

        bst.addNode("a");
        bst.addNode("e");

        bst.addNode("g");
        bst.addNode("h");


        bst.remove("c");

        bst.printTree(bst.getRoot());
    }
}
