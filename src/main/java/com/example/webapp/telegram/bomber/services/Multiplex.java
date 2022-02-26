package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.JsonService;
import org.json.JSONException;
import org.json.JSONObject;

public class Multiplex extends JsonService {

    public Multiplex() {
        setUrl("https://auth.multiplex.ua/login");
        setMethod(POST);
        setPhoneCode("380");
    }

    @Override
    public String buildJson() {
        JSONObject json = new JSONObject();

        try {
            json.put("login", getFormattedPhone());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
}
