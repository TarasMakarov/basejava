package com.urise.webapp;

public class MainDeadLock {

    public static void locking(River river1, River river2) {
        synchronized (river1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (river2) {
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
            }
        }
    }

    static class River {
    }

        public static void main(String[] args) {
            River river1 = new River();
            River river2 = new River();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    locking(river2, river1);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    locking(river1, river2);
                }
            }).start();
        }
    }