package org.agh.edu.pl.locators;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ServantLocator;
import org.agh.edu.pl.objects.DedicatedServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class DedicatedServiceLocator implements ServantLocator {
    private Map<String, DedicatedServiceImpl> servants = new HashMap<>();
    private int id=1;

    public void finished(Current current, Object servant, java.lang.Object cookie) {
    }

    @Override
    public LocateResult locate(Current curr){
        String name = curr.id.name.toString();
        DedicatedServiceImpl dedicatedService = servants.get(name);
        if (dedicatedService == null) {
            dedicatedService = new DedicatedServiceImpl(id++, name);
            servants.put(name, dedicatedService);
        }
        return new ServantLocator.LocateResult(dedicatedService, null);
    }

    public void deactivate(String category) {}

}
