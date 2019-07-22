package com.buaa.module_simulation;

import com.buaa.module_simulation.app.Simulator;
import com.buaa.module_simulation.bean.Customer;
import com.buaa.module_simulation.server.Server;
import com.buaa.module_simulation.server.WaitingQueue;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final int AMOUNT_OF_SERVERS = 10;
    private static final int OVER_TIME = 120;

    private static ArrayList<Server> servers = new ArrayList<>();
    private static ArrayList<WaitingQueue> queues = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        buildServers();
        buildCustomers();
        Simulator diningSimulator = new Simulator(customers, servers, queues);
        diningSimulator.run();
    }

    private static void buildServers() {
        for (int i = 0; i < AMOUNT_OF_SERVERS; i++) {
            Server server = new Server();
            WaitingQueue queue = new WaitingQueue(server);
            servers.add(server);
            queues.add(queue);
        }
    }

    private static void buildCustomers() {
        int arriveTime = 0;
        while (arriveTime < OVER_TIME) {
            int internalArrivalTime = getInternalArrivalTime();
            int serviceTime = getServiceTime();
            arriveTime += internalArrivalTime;
            Customer customer = new Customer(arriveTime, serviceTime);
            customers.add(customer);
        }
    }

    private static int getInternalArrivalTime() {
        return getRandBetween(0, 10);
    }

    private static int getServiceTime() {
        return getRandBetween(1, 6);
    }

    private static int getRandBetween(int lowBound, int higBound) {
        return new Random().nextInt(higBound - lowBound + 1) + lowBound;
    }
}
