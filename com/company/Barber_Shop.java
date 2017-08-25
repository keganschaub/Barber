package com.company;

/**
 * Created by kegan on 3/30/15.
 */
public class Barber_Shop {
    private int barber = 0, chair = 0, open = 0;
    Condition_Availability barber_available = new Condition_Availability();
    Condition_Availability chair_occupied = new Condition_Availability();
    Condition_Availability door_open = new Condition_Availability();
    Condition_Availability customer_left = new Condition_Availability();

    public void get_haircut() {
        while (barber_available.getAvailability() == false){
        //while(barber == 0) {
            try {
                synchronized (barber_available) {
                    barber_available.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        barber_available.unavailable();
        chair_occupied.available();
        synchronized (chair_occupied) {
            chair_occupied.notify();
        }
        while (door_open.getAvailability() == false){
            try {
                synchronized (door_open) {
                    door_open.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        door_open.unavailable();
        synchronized (customer_left) {
            customer_left.notify();
        }

    }

    public void get_next_customer(){
        barber_available.available();
        synchronized (barber_available) {
            barber_available.notify();
        }
        while(chair_occupied.getAvailability() == false){
            try {
                synchronized (chair_occupied) {
                    chair_occupied.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chair_occupied.unavailable();
    }

    public void finished_cut(){
        door_open.available();
        synchronized (door_open) {
            door_open.notify();
        }
        while(door_open.getAvailability() == true){
            try {
                synchronized (customer_left) {
                    customer_left.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private class Condition_Availability {
        private boolean value = false;
        public boolean getAvailability(){
            return value;
        }
        public void unavailable(){
            value = false;
        }
        public void available(){
            value = true;
        }
    }

}
