package org.agh.edu.pl.server;

import org.agh.edu.pl.gen.Conditions;
import org.agh.edu.pl.gen.GreenhouseConditionsSubscription;
import org.agh.edu.pl.gen.GreenhouseConditionsGrpc.GreenhouseConditionsImplBase;
import io.grpc.stub.StreamObserver;
import org.agh.edu.pl.greenhouses.Greenhouse;
import org.agh.edu.pl.subscribers.Subscriber;

import java.util.*;

public class GreenhouseConditionImpl extends GreenhouseConditionsImplBase {
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<Greenhouse> greenhouses;

    public GreenhouseConditionImpl(List<Greenhouse> greenhouses){
        this.greenhouses = greenhouses;
    }

    @Override
    public void subscribeGreenhouseConditions(GreenhouseConditionsSubscription request,
                                              StreamObserver<Conditions> responseObserver) {
        List<String> greenhousesIds = request.getGreenhousesList();
        int clientID = request.getClientID();
        Subscriber subscriber = findOrCreateSubscriber(clientID, responseObserver);
        var greenhousesToSubscribe = greenhousesIds.stream().map(this::mapIdToGreenhouse).toList();
        greenhousesToSubscribe.forEach(x -> x.addSubscriber(subscriber));
        System.out.println("Subscriber " + clientID + " connected");
    }

    private Subscriber findOrCreateSubscriber(int clientId, StreamObserver<Conditions> responseObserver){
        Optional<Subscriber> optSubscriber = subscribers.stream().filter(x -> x.getClientId()==clientId).findFirst();
        if(optSubscriber.isPresent()){
            Subscriber subscriber = optSubscriber.get();
            subscriber.setResponseObserver(responseObserver);
            return subscriber;
        }
        Subscriber subscriber = new Subscriber(clientId, responseObserver);
        subscribers.add(subscriber);
        return subscriber;
    }

    private Greenhouse mapIdToGreenhouse(String id){
        for(Greenhouse g: greenhouses){
            if(g.getId().equals(id)){
                return g;
            }
        }
        throw new NoSuchElementException();
    }
}
