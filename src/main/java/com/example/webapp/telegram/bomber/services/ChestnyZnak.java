package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.JsonService;
import org.json.JSONException;
import org.json.JSONObject;

public class ChestnyZnak extends JsonService {

    public ChestnyZnak() {
        setUrl("https://mobile.api.crpt.ru/mobile/login");
        setMethod(POST);
    }

    @Override
    public String buildJson() {
        JSONObject json = new JSONObject();

        try {
            json.put("phone", getFormattedPhone());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
}
