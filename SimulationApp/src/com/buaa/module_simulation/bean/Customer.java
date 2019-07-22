package com.buaa.module_simulation.bean;

public class Customer {
    private int arriveTime;
    private int serviceTime;
    private int leaveTime;
    private int waitTime;

    public Customer(int arriveTime, int serviceTime) {
        this.arriveTime = arriveTime;
        this.serviceTime = serviceTime;
    }

    public void setLeaveTime(int leftTime) {
        this.leaveTime = leftTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    @Override
    public String toString() {
        return "Customer [" + hashCode() + "]:\n " +
                "arriveTime is " + arriveTime + "\n " +
                "serviceTime is " + serviceTime + "\n " +
                "leaveTime is " + leaveTime + "\n " +
                "waitTime is " + waitTime;
    }
}
