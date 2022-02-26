package com.example.webapp.telegram.actions.events;

import com.example.webapp.telegram.actions.callbacks.SendCallback;
import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.ButtonGenerator;
import com.example.webapp.telegram.impl.EventImpl;
import com.example.webapp.telegram.service.UserService;
import com.example.webapp.utils.TelegramUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SendPostEvent extends EventImpl {
    public SendPostEvent(String name) {
        super(name);
    }

    @Override
    public void exec(Update update, UserEntity user, String params) {
        user.setEvent(null);
        UserService.saveUser(user);
        InlineKeyboardMarkup keyboardMarkup = null;

        StringBuilder stringBuilder = new StringBuilder();
        if(update.getMessage().getText() != null)
            stringBuilder.append(update.getMessage().getText());
        else
            stringBuilder.append(update.getMessage().getCaption());
        String message = stringBuilder.toString();

        if (message.contains("<button>"))
        {

            keyboardMarkup = generateButton(message);

            message = message.split("<button>")[0];;
        }

        String result = message + "";


        if(update.getMessage().getDocument() == null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(result);
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.enableHtml(true);
            List<InlineKeyboardButton> list;
            if(keyboardMarkup != null) {
                list = generateList(update, sendMessage, keyboardMarkup);
                keyboardMarkup.getKeyboard().add(list);

                sendMessage.setReplyMarkup(keyboardMarkup);

            } else {
                list = generateList(update, sendMessage);
                keyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> lists = new ArrayList<>();
                lists.add(list);

                keyboardMarkup.setKeyboard(lists);

                sendMessage.setReplyMarkup(keyboardMarkup);

            }
            new RunMessageHandler(sendMessage);
        }
    }

    private List<InlineKeyboardButton> generateList(Update update, SendMessage sendMessage, InlineKeyboardMarkup oldKeyboard)
    {
        String callbackId = "send" + update.getMessage().getMessageId();
        SendMessage sendMessageOld = new SendMessage();

        List<List<InlineKeyboardButton>> oldList = new ArrayList<>(oldKeyboard.getKeyboard());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(oldList);

        sendMessageOld.setReplyMarkup(inlineKeyboardMarkup);
        sendMessageOld.setText(sendMessage.getText());

        new SendCallback(callbackId, sendMessageOld, inlineKeyboardMarkup);

        List<InlineKeyboardButton> list = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Send");
        inlineKeyboardButton.setCallbackData(callbackId);

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setCallbackData("decline");
        inlineKeyboardButton1.setText("Decline");

        list.add(inlineKeyboardButton);
        list.add(inlineKeyboardButton1);

        return list;
    }

    private List<InlineKeyboardButton> generateList(Update update, SendMessage sendMessage)
    {
        String callbackId = "send" + update.getMessage().getMessageId();
        SendMessage sendMessageOld = new SendMessage();

        List<List<InlineKeyboardButton>> oldList = new ArrayList<>();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(oldList);

        sendMessageOld.setReplyMarkup(inlineKeyboardMarkup);
        sendMessageOld.setText(sendMessage.getText());

        new SendCallback(callbackId, sendMessageOld, inlineKeyboardMarkup);

        List<InlineKeyboardButton> list = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Send");
        inlineKeyboardButton.setCallbackData(callbackId);

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setCallbackData("decline");
        inlineKeyboardButton1.setText("Decline");

        list.add(inlineKeyboardButton);
        list.add(inlineKeyboardButton1);

        return list;
    }

    private InlineKeyboardMarkup generateButton(String message) {
        String button = message.toString();

        String afterButton = button.split("<button>")[1];
        String insideButton = afterButton.split("</button>")[0];

        String buttonText = insideButton.split("\\[")[0];
        String url = insideButton.split("\\[")[1].replaceAll("\\]","");

        InlineKeyboardMarkup keyboardMarkup = new ButtonGenerator().generate(buttonText,url,false);
        return keyboardMarkup;
    }
}
