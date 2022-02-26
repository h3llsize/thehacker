package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.actions.keyboards.list.StartKeyboard;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.ButtonGenerator;
import com.example.webapp.telegram.generators.MessageGenerator;
import com.example.webapp.telegram.impl.MessageImpl;
import com.example.webapp.telegram.query.referral.ReferralQuery;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartMessage extends MessageImpl {
    public StartMessage(String name) {
        super(name);
    }

    @Override
    public void exec(Update update, UserEntity userEntity) {

        try {
            if (update.getMessage().getText().split(" ").length > 1) {
                executeStartParams(update);
            }
        } catch (Exception ignored) {}

        SendMessage sendMessage =
                generateMessage(update, "\uD83C\uDF0E ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n" +
                        "├ <b>Привет, " + userEntity.getName() + "\n" +
                        "├ Спасибо за то что выбрал нас \n" +
                        "├ Наша команда старалась над этим проектом \n" +
                        "├ Если возникнут вопросы, обращайся к менеджеру\n" +
                        "├ Ссылку на него можешь найти в описании к боту</b>\n" +
                        "\uD83C\uDF0E ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(new StartKeyboard());

        userEntity.setEvent(null);
        UserService.saveUser(userEntity);
        new RunMessageHandler(sendMessage);

    }

    private void executeStartParams(Update update)
    {
        String[] params = update.getMessage().getText().split(" ");
        if(params[1].startsWith("r_"))
        {
            try {
                UserEntity referral = UserService.getUser(update.getMessage().getChatId());
                String code = params[1];

                ReferralQuery referralQuery = new ReferralQuery(referral, code);
                String message = referralQuery.execute();

                new MessageGenerator(message, referral.getId());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
