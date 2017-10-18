package com.cy.util;


import com.cy.data.UString;
import org.joor.Reflect;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UTree {

    private String data = "data";
    private String left = "left";
    private String right = "right";
    private Class clazz;

    public UTree setData(String data) {
        this.data = data;
        return this;
    }

    public UTree setLeft(String left) {
        this.left = left;
        return this;
    }

    public UTree setRight(String right) {
        this.right = right;
        return this;
    }

    public UTree setClazz(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    public static UTree instance() {
        return new UTree();
    }

    // 左边小，右边大，相等时放左边
    public <T> T createOrderTree(int[] a) {
        T root = (T) Reflect.on(clazz).create().get();
        if (a == null || a.length == 0) return null;

        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                Reflect.on(root).set(data, a[i]);
            } else {
                T leaf = (T) Reflect.on(clazz).create().get();
                Reflect.on(leaf).set(data, a[i]);

                subCreateOrderTree(root, leaf);
            }
        }

        return root;
    }

    private <T> void subCreateOrderTree(T root, T leaf) {
        int rootData = (Reflect.on(root).get(data));
        int leafData = (Reflect.on(leaf).get(data));
        T leftNode = Reflect.on(root).get(left);
        T rightNode = Reflect.on(root).get(right);

        if (rootData >= leafData) {// == 放左边
            if (leftNode == null) {
                Reflect.on(root).set(left, leaf);
            } else {
                subCreateOrderTree(leftNode, leaf);
            }
        } else {                     // < 放右边
            if (rightNode == null) {
                Reflect.on(root).set(right, leaf);
            } else {
                subCreateOrderTree(rightNode, leaf);
            }
        }

    }

    public <T> List<T> preOrder(T root) {
        List<T> list = new ArrayList<>();
        subPreOrder(root, list);
        return list;
    }

    private <T> void subPreOrder(T root, List<T> list) {
        if (root == null) {
            return;
        }
        list.add(Reflect.on(root).get());
        subPreOrder(Reflect.on(root).get(left), list);
        subPreOrder(Reflect.on(root).get(right), list);
    }


    public <T> List<T> midOrder(T root) {
        List<T> list = new ArrayList<>();
        subMidOrder(root, list);
        return list;
    }

    private <T> void subMidOrder(T root, List<T> list) {
        if (root == null) {
            return;
        }
        subMidOrder(Reflect.on(root).get(left), list);
        list.add(Reflect.on(root).get());
        subMidOrder(Reflect.on(root).get(right), list);
    }


    public <T> List<T> lastOrder(T root) {
        List<T> list = new ArrayList<>();
        subLastOrder(root, list);
        return list;
    }

    private <T> void subLastOrder(T root, List<T> list) {
        if (root == null) {
            return;
        }
        subLastOrder(Reflect.on(root).get(left), list);
        subLastOrder(Reflect.on(root).get(right), list);
        list.add(Reflect.on(root).get());
    }


    public <T> int totalFloor(T root) {
        if (root == null) return 0;
        return subTotalFloor(root, 0);
    }

    private <T> int subTotalFloor(T root, int floor) {
        if (root != null) {
            floor++;
            int leftTotalFloor = subTotalFloor(Reflect.on(root).get(left), floor);
            int rightTotalFloor = subTotalFloor(Reflect.on(root).get(right), floor);
            return Math.max(leftTotalFloor, rightTotalFloor);
        } else return floor;
    }

    public <T> void printNode(T node) {
        List<Point> points = new ArrayList<Point>();
        // 将tree的节点转化为坐标


        buildPoints(node, points, 2);

        // 按照  row,col 对 坐标进行排序方便打印
        Collections.sort(points);

        StringBuilder sb = new StringBuilder();
        int currentRow = 0;
        for (Point p : points) {
            if (p.row == currentRow) {
                sb.append(UString.repeat(" ", 1 * p.col - sb.length()));
                sb.append(p.data);

            }else {
                System.out.println(sb.toString());
                UFile.write_UTF8(new File("a.txt"), sb.toString() + "\n", true);

                sb=new StringBuilder();
                currentRow++;
                sb.append(UString.repeat(" ", 1 * p.col - sb.length()));
                sb.append(p.data);
            }

        }
    }

    static class Point implements Comparable<Point> {
        public Point(int row, int col, int data) {
            this.row = row;
            this.col = col;
            this.data = data;
        }

        int row = 0;
        int col = 0;
        int data = -1;

        @Override
        public int compareTo(Point o) {

            if (this.row == o.row) {
                if (col == o.col)
                    return 0;
                else if (col < o.col) {
                    return -1;
                } else {
                    return 1;
                }

            } else if (row < o.row) {
                return -1;
            } else {
                return 1;
            }
        }

    }

    private <T> void buildPoints(T root, List<Point> points, int padding) {
        int floors = UTree.instance().totalFloor(root);
        int maxCellLength = maxCellLength(root);
        subBuildPoints(root, points, 0, -1, floors, maxCellLength, padding);
    }

    /**
     * @param root
     * @param points
     * @param row    row   小 → 大
     * @param col
     * @param floors 当前节点及以下共有多少层floor  大 → 小
     */
    private <T> void subBuildPoints(T root, List<Point> points, int row, int col, int floors, int maxCellLength, int padding) {
        if (root != null) {
            int inter = (int) ((Math.pow(2, floors - 1) / 2) * (maxCellLength + padding));   //8 = maxCellLenth + padding
            if (col == -1) {
                col = inter;
            }
            /**
             *                           ********000******
             *                           ***000*******000**
             *                           000**000**000**000
             *
             */
            points.add(new Point(row, col, Reflect.on(root).get(data)));
            //fixme -1 +1 padding/2
            subBuildPoints(Reflect.on(root).get(left), points, row + 1, col - ((inter - 1) / 2) - 1, floors - 1, maxCellLength, padding);
            subBuildPoints(Reflect.on(root).get(right), points, row + 1, col + (inter - 1) / 2 + 1, floors - 1, maxCellLength, padding);
        }
    }

    private <T> int maxCellLength(T node) {
        int maxLength = 0;
        List<T> list = preOrder(node);
        for (T node1 : list) {
            int tempLength = Reflect.on(node1).get(data).toString().length();
            if (tempLength > maxLength) {
                maxLength = tempLength;
            }
        }
        return maxLength;
    }
}
