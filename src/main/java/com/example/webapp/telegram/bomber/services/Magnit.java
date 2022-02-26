package com.example.webapp.telegram.bomber.services;

import okhttp3.Request;
import okhttp3.Response;

public class Magnit extends Gorparkovka {

    public Magnit() {
        setUrl("https://new.moy.magnit.ru/local/ajax/login");
        setMethod(POST);
        setPhoneCode("7");
    }

    @Override
    public Request buildRequest(Request.Builder builder) {
        builder.addHeader("Device-Platform", "Android");
        builder.addHeader("x-device-platform", "Android");
        builder.addHeader("x-app-version", "6.18.5");
        return super.buildRequest(builder);
    }
}
