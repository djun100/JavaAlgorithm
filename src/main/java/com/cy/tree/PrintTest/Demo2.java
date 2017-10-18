package com.cy.tree.PrintTest;

public class Demo2 {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
//        bst.insert(55,null);
//        bst.insert(15,null);
//        bst.insert(35,null);
//        bst.insert(25,null);
//
//       bst.insert(10, "a");
//        bst.insert(12, "b");
//        bst.insert(3, "d");
//        bst.insert(9, "cdd");
//        bst.insert(33, "cff");
//        bst.insert(38, "ceee");
//        bst.insert(1, "aaaa");
//        bst.insert(0, "dddd");
//        bst.insert(99, "dddd");
//        bst.insert(100, "dddd");
//        bst.insert(7, "dddd");
//        bst.insert(1, "dddd");

//        bst.insert(3333, "a");
//        bst.insert(735, "dddd");
//        bst.insert(22, "b");
//        bst.insert(24, "d");
        bst.insert(87, "cdd");
        bst.insert(38, "cff");
        bst.insert(83, "ceee");
        bst.insert(255, "aaaa");
//        bst.insert(0, "dddd");
        bst.insert(93, "dddd");
        bst.insert(10, "dddd");
//        bst.insert(264, "dddd");

        //从根开始打印
        TreePrintUtil.pirnt(bst.getRoot());

    }
}