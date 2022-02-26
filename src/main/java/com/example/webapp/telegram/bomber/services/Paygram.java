package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.FormService;
import okhttp3.FormBody;

public class Paygram extends FormService {

    public Paygram() {
        setUrl("https://api.paygram.io/join");
        setMethod(POST);
    }

    @Override
    public void buildBody(FormBody.Builder builder) {
        builder.add("phone", getFormattedPhone());
    }
}
