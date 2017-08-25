package com.company;

public class Barber {

    public static void main(String[] args) {
        int nCustomers, haircutTime, arrivalTime;
        Barber_Shop BS = new Barber_Shop();
        Customer customer;
        Barber_Person barber_person;

        if (args.length < 3) {
            System.err.println("Too few arguments");
            System.exit(1);
        }

        nCustomers = Integer.parseInt(args[0]);
        haircutTime = Integer.parseInt(args[1]);
        arrivalTime = Integer.parseInt(args[2]);

        for (int i = 0; i < nCustomers; i++){
            customer = new Customer(BS);
            customer.start();
            try{
                Thread.sleep(arrivalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        barber_person = new Barber_Person(haircutTime, nCustomers, BS);
        barber_person.start();


    }
}
