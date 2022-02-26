package com.example.webapp.telegram.parser;

import com.example.webapp.telegram.bomber.AuthProxy;
import com.example.webapp.telegram.parser.services.FileProxy;
import com.example.webapp.telegram.parser.services.FreeProxyCz;
import com.example.webapp.telegram.parser.services.HideMyName;
import com.example.webapp.telegram.parser.services.MyProxy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class ParserStorage extends Thread {

    public static final HashSet<ServiceImpl> services = new HashSet<>();
    public static final List<AuthProxy> proxies = new ArrayList<>();
    public static boolean isUpdating;

    @PostConstruct
    private void init() {
        start();
    }

    private static void execute() {
        System.out.println("PARSER STORAGE IS STARTING");
        isUpdating = true;
        for (ServiceImpl service : services)
        {
            service.start();
            proxies.addAll(service.proxies);
        }
        isUpdating = false;
    }

    @Override
    public void run() {
        execute();
    }
}
