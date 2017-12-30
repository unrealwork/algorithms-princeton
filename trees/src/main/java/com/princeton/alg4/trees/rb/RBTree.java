package com.princeton.alg4.trees.rb;

public class RBTree<T extends Comparable<T>, S> {
    private Node<T, S> root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public S get(T key) {
        Node<T, S> it = root;
        if (root == null) {
            return null;
        }
        return null;
    }


    private Node<T, S> rotateLeft(Node<T, S> node) {
        if (!isRed(node.getRight())) {
            throw new IllegalArgumentException();
        }
        Node<T, S> wrongRight = node.getRight();
        Node<T, S> between = wrongRight.getLeft();
        node.setRight(between);
        wrongRight.setLeft(node);
        wrongRight.setColor(node.getColor());
        node.setColor(RED);
        return wrongRight;
    }


    private Node<T, S> rotateRight(Node<T, S> node) {
        if (!isRed(node.getLeft())) {
            throw new IllegalArgumentException();
        }
        Node<T, S> wrongLeft = node.getLeft();
        Node<T, S> between = wrongLeft.getRight();
        node.setLeft(between);
        wrongLeft.setLeft(node);
        wrongLeft.setColor(node.getColor());
        node.setColor(RED);
        return wrongLeft;
    }

    private boolean isRed(Node<T, S> node) {
        return node.getColor() == RED;
    }
}
