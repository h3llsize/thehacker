package com.example.webapp.telegram.query.referral;

import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.MessageGenerator;
import com.example.webapp.telegram.service.UserService;

public class ReferralQuery {
    private final String code;
    private final UserEntity referral;
    public ReferralQuery(UserEntity referral, String code) {
        this.code = code;
        this.referral = referral;
    }

    public String execute() {
        UserEntity inviter = null;

        if(referral.getReferralCode().equals(code))
            return "Ты серьёзно хочешь стать своим боссом?";

        try {
            System.out.println(code);
            inviter = UserService.findUserByReferralCode(code);
        } catch (Exception e) {
            return "Такого реферального кода не существует";
        }

        if(referral.getId().equals(inviter.getId()))
        {
            return "Абузить не получиться\\!";
        }

        try {
            inviter.addReferral(referral.getId());
        } catch (Exception e) {
            return e.getMessage();
        }
        referral.setInviter(inviter.getId());

        UserService.saveUser(inviter);
        UserService.saveUser(referral);

        new MessageGenerator("К вам успешно пришёл 1 реферал\\. Количество рефералов : " + inviter.getReferralsList().size(),
                inviter.getChatId());

        return "Вы успешно стали рефералом";
    }
}
