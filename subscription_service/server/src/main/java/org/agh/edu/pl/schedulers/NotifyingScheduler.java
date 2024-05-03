package org.agh.edu.pl.schedulers;

import org.agh.edu.pl.greenhouses.Greenhouse;
import org.agh.edu.pl.subscribers.Subscriber;

import java.util.List;

public class NotifyingScheduler extends Thread {

    List<Greenhouse> greenhouses;

    public void run() {
        while (true) {
            greenhouses.forEach(greenhouse -> {
                var subscribers = greenhouse.getSubscribers();
                if (subscribers != null && !subscribers.isEmpty()) {
                    if (greenhouse.getConditionsToSend() != null) {
                        for (Subscriber subscriber : subscribers){
                            subscriber.bufferMessage(greenhouse.getConditionsToSend());
                        }
                        for (Subscriber subscriber : subscribers) {
                            try {
                                subscriber.notifySubscriber();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            });
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public NotifyingScheduler(List<Greenhouse> greenhouses){
        this.greenhouses = greenhouses;
    }






}