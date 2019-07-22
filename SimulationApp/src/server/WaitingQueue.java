package server;

import bean.Customer;

import java.util.ArrayList;

public class WaitingQueue {
    private Server server;
    private ArrayList<Customer> customers;

    public WaitingQueue(Server server) {
        this.server = server;
        this.customers = new ArrayList<>();
    }

    public void update(int currentTime) {
        tryToAddCustomerToServer(currentTime);
        addAllCustomersInQueueWaitingTime();
        server.update();
    }

    private void tryToAddCustomerToServer(int currentTime) {
        if (!server.isBusy() && customers.size() > 0) {
            server.setCustomer(customers.get(0));
            customers.get(0).setLeaveTime(currentTime + customers.get(0).getServiceTime());
            customers.remove(0);
        }
    }

    private void addAllCustomersInQueueWaitingTime() {
        for (Customer customer : customers) {
            customer.setWaitTime(customer.getWaitTime() + 1);
        }
    }

    public void addCustomerToQueue(Customer customer) {
        customers.add(customer);
    }

    public boolean isEmpty() {
        return customers.isEmpty();
    }
}
