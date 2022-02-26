package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.actions.keyboards.list.MyAccountKeyboard;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyAccountMessage extends MessageImpl {
    public MyAccountMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, UserEntity userEntity) {
        StringBuilder message = new StringBuilder();
        message.append("⚡️ ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n");
        message.append("├ \uD83D\uDDD2 <b>Твоя реферальная ссылка </b>" + "\n├ <code>https://telegram.me/" + Bot.name +"?start=" +
                userEntity.getReferralCode() + "</code>\n");
        message.append("├ \uD83D\uDC41 <b>Твое количество рефералов : </b>" + userEntity.getReferralsList().size() + "\n");
        message.append("├ \uD83D\uDCB3 <b>Твой баланс : </b>" + userEntity.getBalance() + " рублей\n" +
                "⚡️ ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        SendMessage sendMessage = generateMessage(action,message.toString());
        sendMessage.setReplyMarkup(new MyAccountKeyboard());
        new RunMessageHandler(sendMessage);
    }
}
