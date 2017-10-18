package com.cy.tree;


import com.cy.data.UList;
import com.cy.util.UTree;

/**
 * 二叉树排序， 并树形打印结果
 *
 * @author 承影
 */
public class OrderTree {

    public static void main(String[] args) {
        /**
         *                        ***4***
         *                        *2***6*
         *                        1*3*5*7
         *
         */
//        int[] a = {1, 2, 4, 3, 6, 5, 7, 8, 9};
        int[] a = {6, 4, 2, 1, 3, 4, 5, 7, 8, 9};
//        int[] a = {64,434,2343,13,32323,424,523,72,84234,924234,425,426,520,526,510,528,521,525};

        Node rootNode = UTree.instance().setClazz(Node.class).createOrderTree(a);
        System.out.println(UTree.instance().preOrder(rootNode));
        UTree.instance().printNode(rootNode);
    }

    static class Node {
        public Node left;
        public Node right;
        public int data;

        @Override
        public String toString() {
            return data+"";
        }
    }
}