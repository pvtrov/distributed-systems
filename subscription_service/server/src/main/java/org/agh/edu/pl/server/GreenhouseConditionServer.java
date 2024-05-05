package org.agh.edu.pl.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Executors;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.agh.edu.pl.greenhouses.Greenhouse;
import org.agh.edu.pl.schedulers.ConditionsGenerator;
import org.agh.edu.pl.schedulers.NotifyingScheduler;

public class GreenhouseConditionServer {
    private String address = "127.0.0.5";
    private int port = 50566;
    private Server server;
    private SocketAddress socket;
    private ConditionsGenerator cg;
    NotifyingScheduler ns;

    public void start() throws IOException{
        try {
            socket = new InetSocketAddress(InetAddress.getByName(address), port);
        } catch(UnknownHostException e) {};

        List<Greenhouse> greenhouseList = List.of(
                new Greenhouse("1"), new Greenhouse("2"), new Greenhouse("3"), new Greenhouse("4")
        );
        GreenhouseConditionImpl greenhouseCondition = new GreenhouseConditionImpl(greenhouseList);
        cg = new ConditionsGenerator(greenhouseList);
        cg.start();
        ns = new NotifyingScheduler(greenhouseList);
        ns.start();

        server = NettyServerBuilder.forAddress(socket).executor(Executors.newFixedThreadPool(10))
                .addService(greenhouseCondition)
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down gRPC server");
                GreenhouseConditionServer.this.stop();
                System.err.println("server shut down");
            }
        });
    }
    public void stop(){
        if(server!=null){
            server.shutdown();
        }
        ns.interrupt();
        cg.interrupt();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        final GreenhouseConditionServer server = new GreenhouseConditionServer();
        server.start();
        server.blockUntilShutdown();
    }
}
