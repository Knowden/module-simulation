package com.buaa.module_simulation.app;

import com.buaa.module_simulation.bean.Customer;
import com.buaa.module_simulation.server.Server;
import com.buaa.module_simulation.server.WaitingQueue;
import java.util.ArrayList;
import java.util.Random;

public class Simulator {

    private ArrayList<Customer> customers;
    private ArrayList<Server> servers;
    private ArrayList<WaitingQueue> queues;

    private int currentTime = 0;

    public Simulator(ArrayList<Customer> customers,
                     ArrayList<Server> servers,
                     ArrayList<WaitingQueue> queues) {
        this.customers = customers;
        this.servers = servers;
        this.queues = queues;
    }

    public void run() {
        while (!isFinish()) {
            findCustomerToAdd();
            updateAllWaitingQueue();
            currentTime++;
        }
    }

    private boolean isFinish() {
        boolean allCustomersIn = isAllCustomersIn();
        boolean allQueueEmpty = isAllQueueEmpty();
        boolean allServerIdle = isAllServerIdle();
        return allCustomersIn  && allQueueEmpty && allServerIdle;
    }

    private boolean isAllCustomersIn() {
        Customer lastCustomer = customers.get(customers.size() - 1);
        return currentTime > lastCustomer.getArriveTime();
    }

    private boolean isAllQueueEmpty() {
        for (WaitingQueue queue : queues) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllServerIdle() {
        for (Server server : servers) {
            if (server.isBusy())  {
                return false;
            }
        }
        return true;
    }

    private void findCustomerToAdd() {
        for (Customer customer : customers) {
            if (customer.getArriveTime() ==  currentTime) {
                int queueIndex = chooseOneQueueToJoin();
                queues.get(queueIndex).addCustomerToQueue(customer);
            }
        }
    }

    private int chooseOneQueueToJoin() {
        int sizeOfQueues = queues.size();
        return new Random().nextInt(sizeOfQueues);
    }

    private void updateAllWaitingQueue() {
        for (WaitingQueue queue : queues) {
            queue.update(currentTime);
        }
    }
}
