package com.princeton.alg4.trees.rb;

public class Node<T extends Comparable<T>, S> {
    private T key;
    private S value;
    private Node<T, S> left;
    private Node<T, S> right;
    private boolean color;

    public Node(T key, S value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }


    public Node<T, S> getLeft() {
        return left;
    }

    public Node<T, S> getRight() {
        return right;
    }

    public boolean getColor() {
        return color;
    }

    public S getValue() {
        return value;
    }

    public void setLeft(Node<T, S> node) {
        this.left = node;
    }

    public void setRight(Node<T, S> right) {
        this.right = right;
    }

    public void setColor(boolean black) {
    }
}
