package org.agh.edu.pl.schedulers;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.agh.edu.pl.conditions.GreenhouseConditions;
import org.agh.edu.pl.greenhouses.Greenhouse;

import java.util.List;

@Setter
@AllArgsConstructor
public class ConditionsGenerator extends Thread {
    List<Greenhouse> greenhouses;

    public void run(){
        GreenhouseConditions greenhouseConditions = new GreenhouseConditions();
        while (true) {
            greenhouses.forEach(greenhouse -> {
                var newConditions = greenhouseConditions.createConditionsForGreenhouse();
                greenhouse.setConditionsToSend(newConditions);
            });
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
