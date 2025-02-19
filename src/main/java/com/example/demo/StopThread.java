package com.example.demo;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static boolean stopRequested;
    private static synchronized void setStopRequested() {
        stopRequested=true;
    }
    private static synchronized boolean getStopRequested(){
        return stopRequested;
    }
    private volatile static long sN=0;
    //public synchronized static long generateSN(){
    public static long generateSN(){
        long x=sN++;
        System.out.println("   "+x);
        return x;
    }
    public static void main(String[] args) throws InterruptedException{
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(generateSN());
            }
        });
        Thread concurentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(generateSN());
            }
        });
        backgroundThread.start();
        concurentThread.start();
    }
}
