package org.agh.edu.pl.greenhouses;

import lombok.Getter;
import lombok.Setter;
import org.agh.edu.pl.gen.Conditions;
import org.agh.edu.pl.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Greenhouse {
    private final String id;

    private volatile Conditions conditionsToSend;

    private List<Subscriber> subscribers = new ArrayList<>();

    public Greenhouse(String id) {
        this.id = id;
    }

    public void addSubscriber(Subscriber subscriber){
        if (!subscribers.contains(subscriber)){
            this.subscribers.add(subscriber);
        }
    }
}
