package com.buaa.module_simulation.server;

import com.buaa.module_simulation.bean.Customer;

public class Server {
    private int idleTime;
    private int leftWorkTime;

    public Server() {
        idleTime = 0;
        leftWorkTime = 0;
    }

    void update() {
        if (leftWorkTime > 0) {
            leftWorkTime--;
        } else {
            idleTime++;
        }
    }

    void setCustomer(Customer customer) {
        this.leftWorkTime = customer.getServiceTime();
    }

    public boolean isBusy() {
        return leftWorkTime > 0;
    }

    public int getIdleTime() {
        return idleTime;
    }

    @Override
    public String toString() {
        return "Server [" + hashCode() + "] : idleTime is" + idleTime;
    }
}
