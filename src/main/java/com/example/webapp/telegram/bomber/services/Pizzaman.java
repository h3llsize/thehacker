package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.FormService;
import okhttp3.FormBody;

public class Pizzaman extends FormService {

    public Pizzaman() {
        setUrl("https://pizzaman.ru/index.php?route=pizzaman/user/sendCode");
        setMethod(POST);
        setPhoneCode("7");
    }

    @Override
    public void buildBody(FormBody.Builder builder) {
        builder.add("user", format(phone, "+7+(***)+**-**-***"));
    }
}
