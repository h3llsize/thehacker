package com.example.webapp.telegram.parser;

import com.example.webapp.telegram.bomber.AuthProxy;
import okhttp3.Credentials;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

public abstract class ServiceImpl {
    public ServiceImpl() {
        ParserStorage.services.add(this);
    }

    public final List<AuthProxy> proxies = new ArrayList<>();

    public abstract void start();

    protected AuthProxy generateAuthProxy(String ip, String port) {
        int iPort = Integer.parseInt(port);
        AuthProxy authProxy = new AuthProxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(ip,iPort), Credentials.basic("root", "root"));
        return authProxy;
    }
}
