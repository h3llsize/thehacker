package com.example.webapp.telegram.actions.handles;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ActionHandler
{
    public static void execute(Update update) throws Exception{
        if (update.hasMessage()) {
            messageExecute(update);
        }
        else if (update.hasCallbackQuery()) {
            callbackExecute(update);
        }
        else if(update.hasPreCheckoutQuery())
        {
            preCheckoutExecute(update);
        }
    }

    private static void messageExecute(Update update)
    {
        ExecuteMessageHandler executor = new ExecuteMessageHandler();
        executor.execute(update, false);
    }

    private static void callbackExecute(Update update)
    {
        ExecuteMessageHandler executor = new ExecuteMessageHandler();
        executor.execute(update, true);
    }

    private static void preCheckoutExecute(Update update)
    {

    }
}
