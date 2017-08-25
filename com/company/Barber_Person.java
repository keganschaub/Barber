package com.company;

/**
 * Created by kegan on 3/30/15.
 */
public class Barber_Person extends Thread {
    int haircutTime, nCustomers;
    Barber_Shop BS;
    public Barber_Person(int haircutTime, int nCustomers, Barber_Shop BS){
        this.haircutTime = haircutTime;
        this.nCustomers = nCustomers;
        this.BS = BS;
    }

    public void run(){
        for (int i = 0; i < nCustomers; i++) {
            BS.get_next_customer();
            try {
                Thread.sleep(haircutTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BS.finished_cut();
            System.out.println("Gave haircut");
        }
    }
}
