package com.example.webapp.telegram.query.referral;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.MessageGenerator;
import com.example.webapp.telegram.service.UserService;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class CheckReferralsQuery
{
    private final UserEntity user;

    public CheckReferralsQuery(UserEntity user)
    {
        this.user = user;
        execute();
    }

    public void execute() {
        ArrayList<UserEntity> badReferrals = new ArrayList<>();
        StringBuilder badReferralNames = new StringBuilder();

        user.getReferralsList().forEach(z -> {
            try {
                boolean enj = isEnjoyed(z);

                if(!enj)
                {
                    UserEntity referral = UserService.getUser(z);
                    referral.setInviter(null);

                    System.out.println(z);
                    badReferrals.add(referral);

                    new MessageGenerator("Вы перестали быть рефералом для " + user.getName(), referral.getId());

                    UserService.saveUser(referral);
                }
            } catch (Exception e) {
                UserService.saveUser(user);
                e.printStackTrace();
            }
        });

        if (badReferrals.size() == 1)
        {
            user.removeReferral(badReferrals.get(0).getId());
            new MessageGenerator("Пользователь с ником  `" + badReferrals.get(0).getName() + "`  перестал быть вашим рефералом, он покинул канал", user.getId());
        }

        if(badReferrals.size() > 1) {
            badReferrals.forEach(z -> {
                user.removeReferral(z.getId());
                badReferralNames.append(z.getName() + "` `");
            });

            new MessageGenerator("Пользователи с никами  `" + badReferralNames + "`  перестали быть вашим рефералом, они покинули канал", user.getId());
        }
        UserService.saveUser(user);
    }

    private boolean isEnjoyed(Long user)
    {
        GetChatMember chatMember = new GetChatMember();
        chatMember.setUserId(user);
        chatMember.setChatId("-1001426146281");
        try {
            String userStatus = new Bot().execute(chatMember).getStatus();

            return !userStatus.equals("kicked") && !userStatus.equals("left") && !userStatus.equals("restricted") && !userStatus.equals("banned");

        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }
}
