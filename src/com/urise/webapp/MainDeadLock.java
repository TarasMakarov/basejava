package com.urise.webapp;

public class MainDeadLock {

    public static final Object LOCK1 = new Object();
    public static final Object LOCK2 = new Object();

    public static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }


    static class Thread1 extends Thread {

        @Override
        public void run() {
            synchronized (LOCK1) {
                System.out.println("Lock 1 T1");
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                synchronized (LOCK2) {
                    System.out.println("Lock 2 T1");
                }
            }
        }
    }

    static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (LOCK2) {
                System.out.println("Lock 1 T2");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK1) {
                    System.out.println("Lock 2 T2");
                }
            }
        }

    }
}