package com.example.webapp.telegram.bomber.services;


import com.example.webapp.telegram.bomber.services.utils.Callback;
import com.example.webapp.telegram.bomber.services.utils.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.lang.NonNull;

public class YotaTV extends Service {

    public YotaTV() {
        setPhoneCode("7");
    }

    @Override
    public void run(OkHttpClient client, Callback callback) {
        client.newCall(new Request.Builder()
                .url("https://tv.yota.ru/")
                .get().build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                JSONObject json = new JSONObject();

                try {
                    json.put("msisdn", getFormattedPhone());
                    json.put("password", "91234657");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (String cookie : Objects.requireNonNull(response.headers().get("Set-Cookie")).split(";")) {
                    if (cookie.startsWith("SessionID"))
                        client.newCall(new Request.Builder()
                                .url("https://bmp.tv.yota.ru/api/v10/auth/register/msisdn")
                                .addHeader("Cookie", cookie)
                                .post(RequestBody.create(
                                        MediaType.parse("application/json"), json.toString()))
                                .build()).enqueue(callback);
                }
            }
        });
    }
}
