package com.example.webapp.telegram.generators;

import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.repo.UserRepository;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Random;

public class UserGenerator {
    public final UserEntity userEntity;

    public UserGenerator(Update update)
    {
       userEntity = new UserEntity();

       Long chatID = update.getMessage().getChatId();

       userEntity.setChatId(chatID);
       userEntity.setAdmin(false);
       userEntity.setReferralCode(generateReferralCode());
       userEntity.setBalance(0);

       String name = getUserName(update.getMessage().getChat());

       userEntity.setName(name);

       UserService.saveUser(userEntity);
    }

    public UserGenerator(CallbackQuery update)
    {
        userEntity = new UserEntity();

        Long chatID = update.getMessage().getChatId();

        userEntity.setChatId(chatID);
        userEntity.setAdmin(false);
        userEntity.setBalance(0);
        userEntity.setReferralCode(generateReferralCode());
        userEntity.setName(update.getMessage().getChat().getUserName());

        UserService.saveUser(userEntity);
    }

    private String generateReferralCode()
    {
        Random random = new Random();
        String referral = "r_" + random.nextInt(100000000);
        return referral;
    }

    private String getUserName(Chat chat)
    {
        if(chat.getUserName() != null)
        {
            return chat.getUserName();
        }
        else {
            StringBuilder name = new StringBuilder();
            if(chat.getFirstName() != null)
            {
                name.append(chat.getFirstName());
            }
            if(chat.getLastName() != null)
            {
                name.append(chat.getLastName());
            }
            return name.toString();
        }
    }
}
