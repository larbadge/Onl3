package com.mikhalov.container;

import com.mikhalov.model.Car;

import java.util.Comparator;

public class CarTree extends BinaryTreeRealisation<Car> {
    public CarTree() {
    }

    public CarTree(Comparator<Car> comparator) {
        super(comparator);
    }

    public int summaryCount() {
        return summaryCount(root);
    }

    private int summaryCount(Node node) {
        if (node == null) {
            return 0;
        }
        return node.value.getCount() + summaryCount(node.left) + summaryCount(node.right);
    }
}
