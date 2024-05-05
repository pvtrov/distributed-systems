package org.agh.edu.pl.objects;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Util;
import org.agh.edu.pl.Demo.DedicatedService;

public class DedicatedServiceImpl implements DedicatedService {
    private final int servantId;
    private final String name;

    public DedicatedServiceImpl(int servantId, String name) {
        this.servantId = servantId;
        this.name = name;
        System.out.println("New dedicated servant: " + servantId + " with name " + name + " has been created");
    }

    @Override
    public String sayHello(String name, Current current) {
        System.out.println("saidHello method of servant " + servantId + " called - identity: " + Util.identityToString(current.id));
        return "Hello, " + name + "!";
    }
}
