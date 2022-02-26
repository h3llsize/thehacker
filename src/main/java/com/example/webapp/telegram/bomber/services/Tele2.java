package com.example.webapp.telegram.bomber.services;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Tele2 extends ParamsService {

    public Tele2() {
        setUrl("https://msk.tele2.ru/api/validation/number/");
        setMethod(POST);
    }

    @Override
    public Request buildRequest(Request.Builder builder) {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), "{\"sender\": \"Tele2\"}");

        builder.method(method, body);
        return super.buildRequest(builder);
    }

    @Override
    public void buildParams(HttpUrl.Builder builder) {
        builder.addPathSegment(getFormattedPhone());
    }
}
