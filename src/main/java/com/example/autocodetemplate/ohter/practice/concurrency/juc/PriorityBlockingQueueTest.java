package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * ArrayBlockingQueue
 * LinkedBlockingQueue
 */
@Slf4j
public class PriorityBlockingQueueTest {
    private static int threadNum = 10;
    private static PriorityBlockingQueue<Customer> priorityBlockingQueue = new PriorityBlockingQueue<Customer> (10,new PriorityComparator());

    public static void main(String[] args) throws Exception {
        Executor executor = Executors.newFixedThreadPool(threadNum);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Customer customer = null;
                while (true) {
                    try {
                        Thread.sleep(1000L);
                        customer = priorityBlockingQueue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (customer != null) {
                        customer.buyTiket();
                    }
                }
            }
        });

        thread.setName("售票线程");
        thread.start();


        for (int i = 0; i < threadNum; i++) {
            executor.execute(new QueueForTicket(new Customer("customer" + i, (int) (Math.random() * 10)), priorityBlockingQueue));
            log.info("当前排队购票人数{}", priorityBlockingQueue.size());

            Thread.sleep(500L);
        }
    }

}

@Slf4j
class QueueForTicket implements Runnable {
    private Customer customer;
    private PriorityBlockingQueue<Customer> priorityBlockingQueue;

    public QueueForTicket(Customer customer, PriorityBlockingQueue<Customer> priorityBlockingQueue) {
        this.customer = customer;
        this.priorityBlockingQueue = priorityBlockingQueue;
    }

    @Override
    public void run() {
        priorityBlockingQueue.offer(customer);
        customer.dequeForTiket();
    }
}

@Getter
@Setter
@ToString
class PriorityComparator  implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o2.getMembershipLevel().compareTo(o1.getMembershipLevel());
    }

}

@Getter
@Setter
@ToString
@Slf4j
class Customer  {
    private String name;
    public Customer() {}

    public Customer(String name, Integer order) {
        this.name = name;
        this.membershipLevel = order;
    }
    private Integer membershipLevel;

    public void dequeForTiket() {
        log.info("游客{},会员等级{},开始排队", name, membershipLevel);
    }
    public void buyTiket() {
        log.info("游客{},会员等级{},购买车票", name, membershipLevel);
    }
}