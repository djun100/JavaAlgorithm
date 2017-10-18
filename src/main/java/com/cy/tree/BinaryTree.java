package com.cy.tree;

import java.util.LinkedList;

/**
 * 普通二叉树，非排序二叉树
 */
public class BinaryTree {
    //根节点  
    private Node<Integer> mRoot;

    //二叉树中节点数量  
    private int mSize;

    //无参构造器  
    public BinaryTree() {
        mRoot = new Node<Integer>();
    }

    //数组构造器  
    public BinaryTree(int[] values) {
        System.out.print("新建binaryTree:");
        for (int i : values) {
            System.out.print(i);
        }
        System.out.println();
        boolean isLeft = true;
        int len = values.length;
        if (len == 0)
            return;
        LinkedList<Node<Integer>> queue = new LinkedList<Node<Integer>>();
        mRoot = new Node<Integer>(values[0]);
        queue.addLast(mRoot);
        Node parent = null;
        Node current = null;
        for (int i = 1; i < len; i++) {
            current = new Node<Integer>(values[i]);
            queue.addLast(current);
            if (isLeft)
                parent = queue.getFirst();
            else
                parent = queue.removeFirst();

            if (isLeft) {
                parent.setLeftChild(current);
                isLeft = false;
            } else {
                parent.setRightChild(current);
                isLeft = true;
            }
        }
    }

    //递归中序遍历  
    public void inorder() {
        System.out.print("binaryTree递归中序遍历:");
        inorderTraverseRecursion(mRoot);
        System.out.println();
    }

    //层次遍历  
    public void layerorder() {
        System.out.print("binaryTree层次遍历:");
        LinkedList<Node<Integer>> queue = new LinkedList<Node<Integer>>();
        queue.addLast(mRoot);
        Node<Integer> current = null;
        while (!queue.isEmpty()) {
            current = queue.removeFirst();
            if (current.getLeftChild() != null)
                queue.addLast(current.getLeftChild());
            if (current.getRightChild() != null)
                queue.addLast(current.getRightChild());
            System.out.print(current.getValue());
        }
        System.out.println();
    }

    //获得二叉树深度     
    public int getDepth() {
        return getDepthRecursion(mRoot);
    }

    private int getDepthRecursion(Node<Integer> node) {
        if (node == null)
            return 0;
        int llen = getDepthRecursion(node.getLeftChild());
        int rlen = getDepthRecursion(node.getRightChild());
        int maxlen = Math.max(llen, rlen);
        return maxlen + 1;
    }

    //递归先序遍历      
    public void preorder() {
        System.out.print("binaryTree递归先序遍历:");
        preorderTraverseRecursion(mRoot);
        System.out.println();
    }

    private void inorderTraverseRecursion(Node<Integer> node) {
        // TODO Auto-generated method stub  
        if (node.getLeftChild() != null)
            inorderTraverseRecursion(node.getLeftChild());
        System.out.print(node.getValue());
        if (node.getRightChild() != null)
            inorderTraverseRecursion(node.getRightChild());
    }

    private void preorderTraverseRecursion(Node<Integer> node) {
        System.out.print(node.getValue());
        if (node.getLeftChild() != null)
            preorderTraverseRecursion(node.getLeftChild());
        if (node.getRightChild() != null)
            preorderTraverseRecursion(node.getRightChild());
    }

    //非递归先序遍历     
    public void preorderNoRecursion() {
        System.out.print("binaryTree非递归先序遍历:");
        LinkedList<Node<Integer>> stack = new LinkedList<Node<Integer>>();
        stack.push(mRoot);
        Node<Integer> current = null;
        while (!stack.isEmpty()) {
            current = stack.pop();
            System.out.print(current.getValue());
            if (current.getRightChild() != null)
                stack.push(current.getRightChild());
            if (current.getLeftChild() != null)
                stack.push(current.getLeftChild());
        }
        System.out.println();
    }

    /**
     * 非递归中序遍历
     * 栈内保存将要访问的元素
     */
    public void inorderNoRecursion() {
        System.out.print("binaryTree非递归中序遍历:");
        LinkedList<Node<Integer>> stack = new LinkedList<Node<Integer>>();
        Node<Integer> current = mRoot;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeftChild();
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.getValue());
                current = current.getRightChild();
            }
        }
        System.out.println();
    }

    /**
     * 非递归后序遍历
     * <p>
     * 当上一个访问的结点是右孩子或者当前结点没有右孩子则访问当前结点
     */
    public void postorderNoRecursion() {
        System.out.print("binaryTree非递归后序遍历:");
        Node<Integer> rNode = null;
        Node<Integer> current = mRoot;
        LinkedList<Node<Integer>> stack = new LinkedList<Node<Integer>>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeftChild();
            }
            current = stack.pop();
            while (current != null && (current.getRightChild() == null || current.getRightChild() == rNode)) {
                System.out.print(current.getValue());
                rNode = current;
                if (stack.isEmpty()) {
                    System.out.println();
                    return;
                }
                current = stack.pop();
            }
            stack.push(current);
            current = current.getRightChild();
        }
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(new int[]{1, 9, 3, 4, 5, 6, 7, 8});
        bt.inorder();
        bt.preorder();
        bt.layerorder();
        bt.preorderNoRecursion();
        bt.inorderNoRecursion();
        bt.postorderNoRecursion();
        System.out.println("深度为：" + bt.getDepth());
    }
}

class Node<V> {
    private V mValue;
    private Node<V> mLeftChild;
    private Node<V> mRightChild;

    public Node() {
    }

    public Node(V value) {
        this.mValue = value;
        this.mLeftChild = null;
        this.mRightChild = null;
    }

    public void setLeftChild(Node<V> lNode) {
        mLeftChild = lNode;
    }

    public void setRightChild(Node<V> rNode) {
        mRightChild = rNode;
    }

    public void setValue(V value) {
        mValue = value;
    }

    public V getValue() {
        return mValue;
    }


    public Node<V> getLeftChild() {
        return mLeftChild;
    }

    public Node<V> getRightChild() {
        return mRightChild;
    }


}  