package com.urise.webapp;

public class MainDeadLock {

    public static final Object LOCK1 = new Object();
    public static final Object LOCK2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (LOCK1) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK2) {
                    System.out.println("1 " + Thread.currentThread().getState());
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (LOCK2) {
                synchronized (LOCK1) {
                    System.out.println("2 " + Thread.currentThread().getState());
                }
            }
        }).start();
    }
}