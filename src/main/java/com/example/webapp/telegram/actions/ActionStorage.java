package com.example.webapp.telegram.actions;


import com.example.webapp.telegram.actions.callbacks.DeclineCallback;
import com.example.webapp.telegram.actions.callbacks.InformationCallback;
import com.example.webapp.telegram.actions.callbacks.NumbersHistoryCallback;
import com.example.webapp.telegram.actions.callbacks.SendCallback;
import com.example.webapp.telegram.actions.events.SendPostEvent;
import com.example.webapp.telegram.actions.events.StartBomberEvent;
import com.example.webapp.telegram.actions.events.StartGatherEvent;
import com.example.webapp.telegram.actions.messages.*;
import com.example.webapp.telegram.impl.AdminCommandImpl;
import com.example.webapp.telegram.impl.CallbackImpl;
import com.example.webapp.telegram.impl.EventImpl;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.query.referral.CheckReferralsQuery;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ActionStorage {
    public static final HashMap<String, MessageImpl> messageHashMap = new HashMap<String, MessageImpl>();
    public static final HashMap<String, AdminCommandImpl> adminHashMap = new HashMap<String, AdminCommandImpl>();
    public static final HashMap<String, EventImpl> eventHashMap = new HashMap<>();

    static {
        new StartMessage("/start");
        new UnknownMessage("Unknown");
        new MyAccountMessage("/lk");
        new YesCommand("да");
        new CheckReferalsMessage("/check");
        new StartBomberEvent("startBomber");
        new BomberAttackMessage("/bomber");
        new BomberAttackMessage("\uD83D\uDCA3 Бомбер");
        new MyAccountMessage("\uD83E\uDDE9 Личный кабинет");
        new GetUsersSize("/getusersize");
        new WorldOfScamMessage("\uD83D\uDC8E Лучший телеграмм канал");
        new SendPostMessage("/sendpost");
        new SendPostEvent("sendPost");
        new DeclineCallback("decline");
        new NumbersHistoryCallback("numbers_history");
        new InformationCallback("myaccount_information");

        new GatherStartMessage("/gather");
        new StartGatherEvent("gather");
    }

    public static void addMessageAction(MessageImpl action, String commandName)
    {
        messageHashMap.put(commandName, action);
    }
    public static void addAdminCommand(AdminCommandImpl action, String commandName)
    {
        adminHashMap.put(commandName, action);
    }

    public static void removeMessage(String message)
    {
        messageHashMap.remove(message);
    }

    public static AdminCommandImpl getAdminCommand(String command)
    {
        return adminHashMap.get(command);
    }

    public static MessageImpl getMessage(String command)
    {
        return messageHashMap.get(command);
    }

    public static EventImpl getEvent(String event)
    {
        return eventHashMap.get(event);
    }

    public static void addEvent(EventImpl action, String commandName)
    {
        eventHashMap.put(commandName, action);
    }
}
