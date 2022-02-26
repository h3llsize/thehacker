package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.FormService;
import okhttp3.FormBody;

public class Dodopizza extends FormService {

    public Dodopizza() {
        setUrl("https://dodopizza.kz/api/sendconfirmationcode");
        setMethod(POST);
    }

    @Override
    public void buildBody(FormBody.Builder builder) {
        builder.add("phoneNumber", "+" + getFormattedPhone());
    }
}
