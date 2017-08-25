package com.company;

/**
 * Created by kegan on 3/30/15.
 */
public class Customer extends Thread {
    Barber_Shop BS;

    public Customer(Barber_Shop BS){
        this.BS = BS;
    }

    public void run(){
        BS.get_haircut();
        System.out.println("Got haircut");
    }
}
