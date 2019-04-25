package com.str818.iterator;

public class Client {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator<Integer> iterator = aggregate.createIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
