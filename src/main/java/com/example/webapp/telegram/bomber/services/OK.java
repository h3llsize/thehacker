package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.FormService;
import okhttp3.FormBody;

public class OK extends FormService {

    public OK() {
        setUrl("https://ok.ru/dk?cmd=AnonymRegistrationEnterPhone&st.cmd=anonymRegistrationEnterPhone");
        setMethod(POST);
    }

    @Override
    public void buildBody(FormBody.Builder builder) {
        builder.add("st.r.phone", "+" + getFormattedPhone());
    }
}
