package com.company;


public class Main {

    public static void main(String[] args) throws Exception {
        Resource.i = 5;
        MyThread myThread = new MyThread();
        myThread.setName("one");
        MyThread myThread2 = new MyThread();
        myThread.start();
        myThread2.start();
        myThread.join();
        myThread2.join();
        System.out.println(Resource.i);
    }
}

class MyThread extends Thread {
    Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
       Resource.changeStaticI();
    }
}

class Resource {
    static int i;

    int j;

    public synchronized static void changeStaticI() {
        int i = Resource.i;
        if (Thread.currentThread().equals("one")) {
            Thread.yield();
        }
        i++;
        Resource.i = i;
        new Resource().changeI();
    }


    public void changeI() {
        synchronized (Resource.class) {
            int i = Resource.i;
            if (Thread.currentThread().equals("one")) {
                Thread.yield();
            }
            i++;
            Resource.i = i;
        }
    }
}