package com.galaxy.util;

import java.util.LinkedList;

/**
 * 手写一个双向简单链表
 *
 * @param <T>
 */
public class LinkedListTest<T> {
    private Node<T> head;
    private Node<T> last;
    private int size;

    //获取长度
    public int size() {
        return this.size;
    }

    //添加
    public void add(T t) {
        Node<T> tNode = new Node<>(t);
        if (head == null) {
            tNode.index = size;
            head = tNode;
            last = tNode;
            this.size++;
            return;
        }
        tNode.index = size;
        tNode.next = head;
        head.piv = tNode;
        head = tNode;
        head.piv = last;
        last.next = head;
        this.size++;
    }

    //获取第一个
    public T getFirst() {
        return last.t;
    }

    //获取最后一个
    public T getLast() {
        return head.t;
    }
    //按照索引获取
    public T get(int index) {
        if (index > this.size - 1) throw new ArrayIndexOutOfBoundsException();
        Node<T> node = head;
        while (node.index != index) {
            node = node.next;
        }
        return node.t;
    }
    //按照索引删除，因为使用了索引所以需要遍历一遍更改索引。。。
    public void remove(int index) {
        if (index == 0) {
            last.piv.next = head;
            head.piv = last.piv;
            last = last.piv;
            Node<T> tNode = last;
            while (tNode.index != 0) {
                tNode.index--;
                tNode = tNode.piv;
            }

            size--;
            return;
        }
        if (index == size - 1) {
            last.next = head.next;
            head.next.piv = last;
            head = head.next;
            head.index = --size - 1;
            return;
        }
        Node<T> node = head;
        while (node.index != index) {
            node = node.next();
        }
        Node<T> tNode = node.piv;
        while (tNode.index != 0) {
            tNode.index--;
            tNode = tNode.piv;
        }
        node.piv.next = node.next;
        node.next.piv = node.piv;
        this.size--;
    }


    public static void main(String[] args) {
        LinkedListTest<Integer> list = new LinkedListTest<Integer>();
        LinkedList<Object> objects = new LinkedList<>();

    }


}

/**
 * 节点内部类 头，尾，索引，值
 * @param <T>
 */
class Node<T> {
    Node next;
    Node piv;
    int index;
    T t;

    public Node() {
    }

    public Node(T t) {
        this.t = t;
        this.next = null;
        this.piv = null;
    }

    public Node next() {
        if (this.hasNext()) {
            return this.next;
        } else {
            return null;
        }
    }

    public boolean hasNext() {
        return this.next == null ? false : true;
    }

}
