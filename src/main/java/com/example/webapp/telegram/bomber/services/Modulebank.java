package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.JsonService;
import org.json.JSONException;
import org.json.JSONObject;

public class Modulebank extends JsonService {

    public Modulebank() {
        setUrl("https://my.modulbank.ru/api/v2/registration/nameAndPhone");
        setMethod(POST);
    }

    @Override
    public String buildJson() {
        JSONObject json = new JSONObject();

        try {
            json.put("FirstName", getRussianName());
            json.put("CellPhone", phone);
            json.put("Package", "optimal");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
}
