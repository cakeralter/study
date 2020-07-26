package top.caker.demos.tree;

/**
 * 二叉树 - 有序树[子节点顺序不可调换]
 * 定义：每个节点最多含有两个子树的树称为二叉树
 * <p>
 * 完全二叉树：对于一颗二叉树，假设其深度为d（d>1）。
 * 除了第d层外，其它各层的节点数目均已达最大值，且第d层所有节点从左向右连续地紧密排列，这样的二叉树被称为完全二叉树。
 * <p>
 * 满二叉树：所有叶节点都在最底层的完全二叉树.
 *
 * @author cakeralter
 * @date 2020/5/15
 */
public class BinaryTree {

    /**
     * 二叉树链表实现
     */
    static class Node {
        /**
         * 节点值
         */
        private int val;
        /**
         * 左子树根节点
         */
        private Node left;
        /**
         * 右子树根节点
         */
        private Node right;

        private Node(int val) {
            this.val = val;
        }

        public static Node build(int val) {
            return new Node(val);
        }

        /*public void put(Node node) {
            if (Objects.isNull(node)) {
                throw new NullPointerException("节点为空");
            }
            if (Objects.isNull(this.getLeft())) {
                // 左孩子为null
                this.setLeft(node);
            } else if (Objects.isNull(this.getRight())) {
                // 右孩子为null
                this.setRight(node);
            } else {
                this.getLeft().put(node);
                this.getRight().put(node);
            }
        }*/

        /**
         * 先序遍历
         */
        public void firstOrder() {

        }

        /**
         * 中序遍历
         */
        public void inorder() {

        }

        /**
         * 后序遍历
         */
        public void lastOrder() {

        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
