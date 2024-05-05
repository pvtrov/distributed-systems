package org.agh.edu.pl.objects;

import com.zeroc.Ice.Util;
import org.agh.edu.pl.Demo.SharedService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SharedServiceImpl implements SharedService {
    private final int servantId;

    public SharedServiceImpl(int servantId) {
        this.servantId = servantId;
        System.out.println("New shared servant: " + servantId + " has been created");
    }

    @Override
    public String getTime(com.zeroc.Ice.Current current) {
        System.out.println("getTime method of" + " servant " + servantId + " called - identity: " + Util.identityToString(current.id));
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        return formattedDate;
    }
}
