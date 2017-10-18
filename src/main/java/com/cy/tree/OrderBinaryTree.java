package com.cy.tree;

import java.util.Random;
import java.util.Stack;

/**
 * Created by cy on 2015/10/30.
 */
public class OrderBinaryTree {
    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value=value;
        }
    }
    public static void preOrderRecursive(Node node){
        if (node!=null) {
            System.out.printf("%d ",node.value);
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    public static void inOrderRecursive(Node node){
        if (node!=null){
            inOrderRecursive(node.left);
            System.out.printf("%d ",node.value);
            inOrderRecursive(node.right);
        }
    }
    public static void postOrderRecursive(Node node){
        if (node!=null){
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }
    /**非递归先序遍历的思路：<br>
     * 中→左→右，打印完中，到左的时候，左又是中，要执行先打印，而右节点需要在左大分支处理后处理，正好早期入栈<br>
     * 根节点压入栈，循环进行（弹出打印，依次压入右节点，左节点）*/
    public static void preOrderUnRecursive(Node node){
        if (node!=null){
            Stack<Node> stack=new Stack<>();
            stack.push(node);
//            stack.add(node);
            while (!stack.isEmpty()){
                node=stack.pop();
                System.out.printf("%d ",node.value);
                if (node.right!=null){
                    stack.push(node.right);
                }
                if (node.left!=null){
                    stack.push(node.left);
                }
            }
        }
    }
    /**非递归中序遍历思路：<br>
     * 左→中→右，每组三个节点都是先打印左，所以先打印最左下角<br></>
     * 根节点入栈，循环压入左节点，弹出打印，打印右节点的左侧分支最下方节点*/
    public static void inOrderUnRecursive(Node node){
        if (node==null) return;
        Stack<Node> stack=new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.value + " ");
                node = node.right;
            }
        }
/*        while (node!=null){
            stack.push(node);
            node=node.left;
        }
        while (!stack.isEmpty()){
            node=stack.pop();
            System.out.print(node.value+" ");
            node=node.right;
            //在该循环中，由于此处又需要走上面的循环，所以需要将此二循环合并到一起，因此有如下写法
        }*/

    }
    /**非递归后序遍历双栈法：<br></>
     * 左→右→后,根节点压入s1,循环执行（将弹出的节点放入s2,依次压入非空的左节点，右节点<br>
     * */
    public static void postOrderUnRecursive(Node node){
        if (node!=null){
            Stack<Node> s1=new Stack<>();
            Stack<Node> s2=new Stack<>();
            s1.push(node);
            while (!s1.isEmpty()){
                node=s1.pop();
                s2.push(node);
                if(node.left!=null){
                    s1.push(node.left);
                }
                if (node.right!=null){
                    s1.push(node.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.print(s2.pop().value + " ");
            }
        }


    }

    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }
    public static Node createOrderBinaryTree(int[] array){
        if (array==null||array.length==0) return null;
        Node root=new Node(array[0]);
        for (int i = 1; i < array.length; i++) {
            Node leaf=new Node(array[i]);
            insertNode(root,leaf);
        }
        return root;
    }

    private static void insertNode(Node root, Node leaf) {
        if (leaf.value<=root.value){
            if (root.left==null) root.left=leaf;
            else insertNode(root.left,leaf);
        }else {
            if (root.right==null) root.right=leaf;
            else insertNode(root.right,leaf);
        }
    }

    public static void main(String args[]){
/*        int[] array=new int[10];
        for (int i = 0; i < 10; i++) {
            array[i]= (int) (new Random().nextInt(100));
        }*/
        int[] array={4,4,2,1,3,6,5,7,8,9};
        Node node=createOrderBinaryTree(array);
        System.out.println("先序遍历");
        preOrderRecursive(node);
        System.out.println("\n中序遍历");
        inOrderRecursive(node);
        System.out.println("\n后序遍历");
        postOrderRecursive(node);
        System.out.println("\n非递归先序遍历");
        preOrderUnRecursive(node);
        System.out.println("\n非递归中序遍历");
        inOrderUnRecursive(node);
        System.out.println("\n非递归后序遍历");
        postOrderUnRecursive(node);
        System.out.println("\n指南：中序非递归");
        inOrderUnRecur(node);
    }
}
