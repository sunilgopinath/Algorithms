package com.company;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.UUID;

public class Huffman {

    public static void main(String[] args) throws Exception {
        String path = "/Users/sunilgopinath/Education/Coursera/Algorithms/Course3/Week3/huffman.txt";
        File f = new File(path);
        Scanner sc = new Scanner(f);
        String line;
        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        while(sc.hasNextLine()) {

            line = sc.nextLine();
            Node n = new Node(UUID.randomUUID().toString(), Integer.valueOf(line), null, null);
            heap.offer(n);
        }
        sc.close();

        while(heap.size() > 1) {
            Node left = heap.poll();
            Node right = heap.poll();
//            System.out.println(left);

            Node n = new Node("$", left.frequency + right.frequency, left, right);
            heap.offer(n);
        }
//        System.out.println(heap.peek().left);
        System.out.println(getMinTreeLength(heap.peek()));
        System.out.println(getMaxTreeLength(heap.peek()));
    }


    public static int getMaxTreeLength(Node top) {
        if (top.left == null && top.right == null) {
            return 0;
        }
        int leftLength = 0;
        int rightLength = 0;
        if (top.left != null) {
            leftLength = getMaxTreeLength(top.left);
        }
        if (top.right != null) {
            rightLength = getMaxTreeLength(top.right);
        }
        return 1 + Math.max(leftLength, rightLength);
    }

    public static int getMinTreeLength(Node top) {
        if (top.left == null && top.right == null) {
            return 0;
        }
        int leftLength = 0;
        int rightLength = 0;
        if (top.left != null) {
            leftLength = getMinTreeLength(top.left);
        }
        if (top.right != null) {
            rightLength = getMinTreeLength(top.right);
        }
        return 1 + Math.min(leftLength, rightLength);
    }

    private static class Node implements Comparable<Node>
    {
        protected Node left;
        protected Node right;
        protected String name;
        protected int frequency;
        public Node(String n, int f, Node l, Node r)
        {
            name = n;
            frequency = f;
            left = l;
            right = r;
        }

        @Override
        public int compareTo(Node arg0)
        {
            if(this.frequency < arg0.frequency)
            {
                return -1;
            }
            else if(this.frequency > arg0.frequency)
            {
                return 1;
            }
            return 0;
        }

        public String toString() {

            return "name: " + name + ", frequency: " + frequency + ", left: " + left + ", right: " + right;
        }
    }

}

