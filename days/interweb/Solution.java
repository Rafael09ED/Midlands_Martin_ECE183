package days.interweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        array();
        System.out.println("---");
        linkedList();
    }

    public static void array() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int indexFromBack = Integer.parseInt(br.readLine());
        String[] list = br.readLine().split("\\s+");

        if (indexFromBack >= list.length || indexFromBack < 0)
            System.out.println("NIL");
        else {
            System.out.println(list[list.length - indexFromBack]);
        }
    }

    public static void linkedList() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int indexFromBack = Integer.parseInt(br.readLine());
        SimpleDLList<Integer> list = new SimpleDLList<>();

        {
            String line = br.readLine().trim() + " ";
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : line.toCharArray()) {
                if (c == ' ') {
                    list.add(Integer.parseInt(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                } else
                    stringBuilder.append(c);
            }
        }

        if (indexFromBack >= list.size())
            System.out.println("NIL");
        else {
            System.out.println(list.get(indexFromBack * -1));
        }

        br.close();
    }

    public static class SimpleDLList<T> {
        Node<T> head, tail;
        int size = 0;

        public boolean add(T val) {
            if (head == null)
                head = tail = new Node<>(val, null, null);
            else {
                tail.setNext(new Node<>(val, tail, null));
                tail = tail.getNext();
            }
            size++;
            return true;
        }

        public T get(int index) {
            if (Math.abs(index) > size)
                throw new IndexOutOfBoundsException("Index: " + index + " is greater than size");

            boolean forwards = index >= 0;
            Node<T> ptr = forwards ? head : tail;
            index = Math.abs(index);

            for (int i = forwards ? 0 : 1; i < index; i++)
                ptr = forwards ? ptr.getNext() : ptr.getPrev();

            return ptr.getData();
        }

        public int size() {
            return size;
        }

        public static class Node<T> {
            T data;
            Node<T> prev;
            Node<T> next;

            public Node(T data, Node<T> prev, Node<T> next) {
                this.data = data;
                this.prev = prev;
                this.next = next;
            }

            public T getData() {
                return data;
            }

            public void setData(T data) {
                this.data = data;
            }

            public Node<T> getPrev() {
                return prev;
            }

            public void setPrev(Node<T> prev) {
                this.prev = prev;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }
        }
    }
}