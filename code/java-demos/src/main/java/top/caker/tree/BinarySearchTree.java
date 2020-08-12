package top.caker.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 二叉搜索树
 * 在树中的任意一个节点，其左子树中的每个节点的值，都要小于这个节点的值，而右子树节点的值都大于这个节点的值。
 *
 * @author cakeralter
 * @date 2020/5/15
 */
public class BinarySearchTree {

    @Getter
    @Setter
    static class Node {
        private int val;
        private Node left;
        private Node right;

        private Node(int val) {
            this.val = val;
        }

        public static Node build(int val) {
            return new Node(val);
        }

        public void put(Node node) {
            if (Objects.isNull(node)) {
                throw new NullPointerException();
            }

            if (node.getVal() < this.getVal()) {
                if (Objects.isNull(this.getLeft())) {
                    this.setLeft(node);
                } else {
                    this.getLeft().put(node);
                }
            }
            if (node.getVal() > this.getVal()) {
                if (Objects.isNull(this.getRight())) {
                    this.setRight(node);
                } else {
                    this.getRight().put(node);
                }
            }
        }

        /**
         * 先序遍历
         */
        public void firstOrder() {
            System.out.print(this.getVal() + " ");
            if (Objects.nonNull(this.getLeft())) {
                this.getLeft().firstOrder();
            }
            if (Objects.nonNull(this.getRight())) {
                this.getRight().firstOrder();
            }
        }

        /**
         * 中序遍历
         */
        public void inorder() {
            if (Objects.nonNull(this.getLeft())) {
                this.getLeft().inorder();
            }
            System.out.print(this.getVal() + " ");
            if (Objects.nonNull(this.getRight())) {
                this.getRight().inorder();
            }
        }

        /**
         * 后序遍历
         */
        public void lastOrder() {
            if (Objects.nonNull(this.getLeft())) {
                this.getLeft().lastOrder();
            }
            if (Objects.nonNull(this.getRight())) {
                this.getRight().lastOrder();
            }
            System.out.print(this.getVal() + " ");
        }

        /**
         * 查找
         *
         * @return
         */
        public Node find(int val) {
            if (val == this.getVal()) {
                return this;
            } else if (val > this.getVal()) {
                if (Objects.isNull(this.getRight())) {
                    return null;
                }
                return this.getRight().find(val);
            } else {
                if (Objects.isNull(this.getLeft())) {
                    return null;
                }
                return this.getLeft().find(val);
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
