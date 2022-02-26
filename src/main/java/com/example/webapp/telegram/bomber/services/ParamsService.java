package com.example.webapp.telegram.bomber.services;

import java.util.Objects;

import com.example.webapp.telegram.bomber.services.utils.Callback;
import com.example.webapp.telegram.bomber.services.utils.SimpleBaseService;
import okhttp3.*;

public abstract class ParamsService extends SimpleBaseService {

    public ParamsService(String url, String method, int... countryCodes) {
        super(url, method, countryCodes);
    }

    public ParamsService(String url, int... countryCodes) {
        super(url, null, countryCodes);
    }

    public ParamsService() {
    }

    public void run(OkHttpClient client, Callback callback) {
        HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        buildParams(httpBuilder);

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(httpBuilder.build().toString());

        if (method != null)
            requestBuilder.method(method, RequestBody.create(MediaType.parse(""), ""));

        client.newCall(buildRequest(requestBuilder)).enqueue(callback);
    }

    public abstract void buildParams(HttpUrl.Builder builder);
}
