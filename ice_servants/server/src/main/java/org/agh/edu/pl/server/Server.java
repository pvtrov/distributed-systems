package org.agh.edu.pl.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import org.agh.edu.pl.locators.DedicatedServiceLocator;
import org.agh.edu.pl.objects.SharedServiceImpl;


public class Server {
    public static void main(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1",
                    "tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");

            // add shared service as singleton object to ASM
            SharedServiceImpl sharedService = new SharedServiceImpl(1);
            Identity identity = new Identity("get", "shared");
            adapter.add(sharedService, identity);

            // add servant locator for dedicated servant
            DedicatedServiceLocator dedicatedServiceLocator = new DedicatedServiceLocator();
            adapter.addServantLocator(dedicatedServiceLocator, "dedicated");

            // activate adapter - start serving client
            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                status = 1;
            }
        }
        System.exit(status);
    }
}
