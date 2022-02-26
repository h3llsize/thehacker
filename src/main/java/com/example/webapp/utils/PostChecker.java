package com.example.webapp.utils;

import com.example.webapp.Logger;
import com.example.webapp.telegram.actions.handles.run.RunMessageHandler;
import com.example.webapp.telegram.actions.handles.run.RunPhotoHandler;
import com.example.webapp.telegram.entities.PostEntity;
import com.example.webapp.telegram.generators.ButtonGenerator;
import com.example.webapp.telegram.service.PostService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.time.LocalDate;

public class PostChecker {
    private final Iterable<PostEntity> postList;
    public PostChecker() {
        postList = PostService.getAllPosts();
        checkAllPosts();
    }

    private void checkAllPosts() {
        postList.forEach(postEntity -> {
            if(LocalDate.now().isBefore(postEntity.getDate()))
            {
                sendPost(postEntity);
            }
        });
    }
    private void sendPost(PostEntity postEntity) {
        try {
            if (postEntity.getPhoto() != null) {
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setPhoto(postEntity.getPhoto());
                sendPhoto.setChatId(postEntity.getChannelId());
                sendPhoto.setCaption(postEntity.getMessage());
                sendPhoto.setParseMode("MarkdownV2");

                new RunPhotoHandler(checkButton(sendPhoto, postEntity));
            } else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(postEntity.getChannelId());
                sendMessage.setText(postEntity.getMessage());
                sendMessage.setParseMode("MarkdownV2");

                new RunMessageHandler(checkButton(sendMessage, postEntity));
            }
        } catch (Exception e)
        {
            Logger.sendConsole(e.toString());
        }
    }

    private SendMessage checkButton(SendMessage sendMessage, PostEntity postEntity) {
        if(postEntity.getButton() != null)
        {
            ButtonGenerator buttonGenerator = new ButtonGenerator();
            InlineKeyboardMarkup inlineKeyboardMarkup;

            if(postEntity.getButtonUrl() != null)
                inlineKeyboardMarkup = buttonGenerator.generate(postEntity.getButton(),postEntity.getButtonUrl(),false);
            else
                inlineKeyboardMarkup =  buttonGenerator.generate(postEntity.getButton(),postEntity.getButtonEvent(),true);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendMessage;
    }

    private SendPhoto checkButton(SendPhoto sendPhoto, PostEntity postEntity)
    {
        if(postEntity.getButton() != null)
        {
            ButtonGenerator buttonGenerator = new ButtonGenerator();
            InlineKeyboardMarkup inlineKeyboardMarkup;

            if(postEntity.getButtonUrl() != null)
                inlineKeyboardMarkup = buttonGenerator.generate(postEntity.getButton(),postEntity.getButtonUrl(),false);
            else
                inlineKeyboardMarkup =  buttonGenerator.generate(postEntity.getButton(),postEntity.getButtonEvent(),true);

            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendPhoto;
    }
}
