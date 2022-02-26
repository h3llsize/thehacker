package com.example.webapp.telegram.bomber.services.utils;


import java.io.IOException;

import okhttp3.Call;
import org.springframework.lang.NonNull;

public interface Callback extends okhttp3.Callback {

    void onError(Exception e);

    @Override
    default void onFailure(@NonNull Call call, @NonNull IOException e) {
        onError(e);
    }
}
