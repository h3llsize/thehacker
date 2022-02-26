package com.example.webapp.telegram.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "users", schema = "public")
public class UserEntity {

    @Id
    @Column(unique = true)
    private Long Id;

    private String event;

    @Column(unique = true)
    private String nftAccount;

    private Long inviter;

    private int balance;

    @Column(unique = true)
    private String btcAccount;

    @Column(unique = true)
    private String discordId;

    private String name;

    @Column(nullable = false)
    private boolean admin;

    private String referralCode;

    private final ArrayList<Long> referralsList = new ArrayList<>();

    private ArrayList<String> numbersList = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ArrayList<String> getNumbersList() {
        return numbersList;
    }

    public void setNumbersList(ArrayList<String> numbersList)
    {
        this.numbersList = numbersList;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public ArrayList<Long> getReferralsList() {
        return referralsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Long getChatId() {
        return Id;
    }

    public void setChatId(Long chatId) {
        this.Id = chatId;
    }

    public String getNftAccount() {
        return nftAccount;
    }

    public void setNftAccount(String nftAccount) {
        this.nftAccount = nftAccount;
    }

    public Long getInviter() {
        return inviter;
    }

    public void setInviter(Long inviter) {
        this.inviter = inviter;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getBtcAccount() {
        return btcAccount;
    }

    public void setBtcAccount(String btcAccount) {
        this.btcAccount = btcAccount;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void addReferral(Long user) throws Exception {
        if (referralsList.contains(user))
            throw new Exception("Пользователь уже был вашим рефералом");
        referralsList.add(user);
    }

    public void removeReferral(Long user)
    {
        referralsList.remove(user);
    }

    public void addNumber(String number)
    {
        numbersList.add(number);
    }
}
