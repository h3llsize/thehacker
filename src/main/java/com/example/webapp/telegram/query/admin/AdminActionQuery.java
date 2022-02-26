package com.example.webapp.telegram.query.admin;

import com.example.webapp.Logger;
import com.example.webapp.telegram.entities.PostEntity;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.service.UserService;


public class AdminActionQuery extends Thread {
    private UserEntity user;
    private String nftAccount, btcAccount, discordId, referralCode;
    private int balance;
    private Long inviter;
    private boolean admin;

    private PostEntity postQuery;

    public AdminActionQuery(Long userID)
    {
        try {
            this.user = UserService.getUser(userID);
        } catch (Exception e) {
            Logger.sendConsole("Invalid userID");
        }
    }

    public AdminActionQuery() {
        Logger.sendConsole("AdminActionQuery without User; Don't forget to add ID");
    }

    public AdminActionQuery setUser(Long userID) {
        try {
            this.user = UserService.getUser(userID);
        } catch (Exception e) {
            Logger.sendConsole("Invalid userID");
        }
        return this;
    }

    public void execute() {
        if (user == null) {
            executePost();
            return;
        }

        user.setNftAccount(nftAccount);
        user.setInviter(inviter);
        user.setBalance(balance);
        user.setBtcAccount(btcAccount);
        user.setDiscordId(discordId);
        user.setAdmin(admin);
        user.setReferralCode(referralCode);

        UserService.saveUser(user);
    }

    @Override
    public void run() {
        execute();
    }

    private void executePost() {

    }

    public boolean isEnoughBalance(int balance)
    {
        return user.getBalance() >= balance;
    }

    public AdminActionQuery addBalance(int balance)
    {
        this.balance = user.getBalance() + balance;
        return this;
    }

    public AdminActionQuery setBalance(int balance)
    {
        this.balance = balance;
        return this;
    }

    public AdminActionQuery setNftAccount(String nftAccount)
    {
        this.nftAccount = nftAccount;
        return this;
    }

    public AdminActionQuery setInviter(Long inviterId)
    {
        this.inviter = inviterId;
        return this;
    }

    public AdminActionQuery setBtcAccount(String btcAccount)
    {
        this.btcAccount = btcAccount;
        return this;
    }

    public AdminActionQuery setDiscordId(String discordId)
    {
        this.discordId = discordId;
        return this;
    }

    public AdminActionQuery addReferral(Long referralId) {
        try {
            user.addReferral(referralId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public AdminActionQuery removeReferral(Long referralId) {
        user.removeReferral(referralId);
        return this;
    }

    public AdminActionQuery setReferralCode(String referralCode) {
        this.referralCode = referralCode;
        return this;
    }

    public AdminActionQuery setAdmin(boolean admin)
    {
        this.admin = admin;
        return this;
    }
}
