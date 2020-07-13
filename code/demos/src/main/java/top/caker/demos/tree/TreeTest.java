package top.caker.demos.tree;

import org.junit.Test;

import java.util.SplittableRandom;

/**
 * @author cakeralter
 * @date 2020/5/15
 */
public class TreeTest {

    private final static int[] DATA = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final static int LENGTH = 100;
    private static SplittableRandom random = new SplittableRandom();

    @Test
    public void testBinaryTree() {
    }

    @Test
    public void testBinarySearchTree() {
        BinarySearchTree.Node root = BinarySearchTree.Node.build(LENGTH >> 1);
        for (int i = 0; i < LENGTH; i++) {
            BinarySearchTree.Node node = BinarySearchTree.Node.build(random.split().nextInt(LENGTH));
            root.put(node);
        }

        System.out.print("先序遍历: [ ");
        root.firstOrder();
        System.out.println(" ]");
        System.out.print("中序遍历: [ ");
        root.inorder();
        System.out.println(" ]");
        System.out.print("后序遍历: [ ");
        root.lastOrder();
        System.out.println(" ]");

        System.out.println(root);
    }
}
