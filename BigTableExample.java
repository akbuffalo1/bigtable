package com.bigtable;

/**
 * Created by AndreyKorneychuk on 1/27/2016.
 */
public class BigTableExample {
    public static void main(String[] args){
        BigTable<String> table = new BigTable<>();
        table.setElement("Object1", 1000, 1000);
        table.setElement("Object2", 10000, 10000);
        String object = table.getElement(10000, 10000);
        System.out.println(object);
    }
}

class BigTable<T> {
    Node head = new Node(0, 0);
    Node current = head;

    private class Node {
        private T mValue = null;

        private int currentX;
        private int currentY;
        private Node nextX;
        private Node nextY;

        Node(T value,int x, int y){
            mValue = value;
            currentX = x;
            currentY = y;
        }

        Node(int x, int y){
            currentX = x;
            currentY = y;
        }

        public boolean hasNextX(){
            return nextX != null;
        }

        public boolean hasNextY(){
            return nextY != null;
        }

        public Node getNextX() {
            return nextX;
        }

        public Node getNextY() {
            return nextY;
        }

        private int getCoordX() {
            return currentX;
        }

        private int getCoordY() {
            return currentY;
        }

        private Node setNextX(T obj, int x, int y) {
            nextX = new Node(obj, x, y);
            return nextX;
        }

        public Node setNextX(int x, int y) {
            nextX = new Node(x, y);
            return nextX;
        }

        public Node setNextY(T obj, int x, int y) {
            nextY = new Node(obj, x, y);
            return nextY;
        }

        public Node setNextY(int x, int y) {
            nextY = new Node(x, y);
            return nextY;
        }

        private T getValue(){
            return mValue;
        }
    }

    public T getElement(int x, int y){
        while(head.getCoordX() != x && head.hasNextX()){
            head = head.getNextX();
        }

        while(head.getCoordY() != y && head.hasNextY()){
            head = head.getNextY();
        }

        return head.getValue();
    }

    public void setElement(T obj, int x, int y){
        current = head;
        while(current.getCoordX() <= x && current.hasNextX()){
            current = current.getNextX();
        }

        current = current.setNextX(x, current.getCoordY());

        while(current.getCoordY() <= y && current.hasNextY()){
            current = current.getNextY();
        }

        current.setNextY(obj, x, y);
    }
}
