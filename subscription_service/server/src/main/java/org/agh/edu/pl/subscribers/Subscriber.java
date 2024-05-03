package org.agh.edu.pl.subscribers;

import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.agh.edu.pl.gen.Conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    private int failedAttempts = 0;
    private boolean isActive = true;
    private int clientId;
    private StreamObserver<Conditions> responseObserver;
    private List<Conditions> bufferedConditions = new ArrayList<>();

    public Subscriber(int clientId, StreamObserver<Conditions> responseObserver) {
        this.clientId= clientId;
        this.responseObserver = responseObserver;
    }

    public void fail() {
        failedAttempts += 1;
    }

    public synchronized void bufferMessage(Conditions conditions) {
        // todo add sorting by date :D
        this.bufferedConditions.add(conditions);
    }

    public void notifySubscriber() throws InterruptedException {
        ServerCallStreamObserver<Conditions> streamObserver = (ServerCallStreamObserver<Conditions>) responseObserver;
        if (streamObserver.isCancelled()) {
            this.fail();
        } else {
            sendMessages();
        }
    }

    private void sendMessages() {
        for(Conditions c: bufferedConditions){
            responseObserver.onNext(c);
        }
        removeMessages();
    }

    private synchronized void removeMessages() {
        this.bufferedConditions.clear();
    }
}
