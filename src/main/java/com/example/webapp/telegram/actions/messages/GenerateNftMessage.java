package com.example.webapp.telegram.actions.messages;

import com.example.webapp.telegram.actions.handles.run.RunPhotoHandler;
import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.impl.ErrorHandler;
import com.example.webapp.telegram.impl.MessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class GenerateNftMessage extends MessageImpl {
    public GenerateNftMessage(String name) {
        super(name);
    }
    private String starMessage = "";
    private String floatMessage = "";

    @Override
    public void exec(Update action, UserEntity userEntity) {
        SendPhoto sendPhoto = new SendPhoto();
        InputStream inputStream = null;
        try {
            inputStream = generateImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendPhoto.setPhoto(new InputFile(inputStream,"Bear"));
        sendPhoto.setChatId(action.getMessage().getChatId().toString());
        sendPhoto.setCaption(starMessage + "\n" + floatMessage);
        new RunPhotoHandler(sendPhoto);
    }

    private InputStream generateImage() throws IOException {

        int imgNum = new Random().nextInt(20);

        BufferedImage bufferedImage = null;
        Image frontBear = null;

        if(imgNum > 18)
        {
            bufferedImage = ImageIO.read(new File("src\\main\\resources\\img\\bear_bg.png"));
            frontBear = ImageIO.read(new File("src\\main\\resources\\img\\bear_front.png"));
        }

        else if(imgNum > 14)
        {
            bufferedImage = ImageIO.read(new File("src\\main\\resources\\img\\monkey_bg.png"));
            frontBear = ImageIO.read(new File("src\\main\\resources\\img\\monkey_front.png"));
        }

        else if(imgNum > 9)
        {
            bufferedImage = ImageIO.read(new File("src\\main\\resources\\img\\olen_bg.png"));
            frontBear = ImageIO.read(new File("src\\main\\resources\\img\\olen_front.png"));
        }

        else
        {
            bufferedImage = ImageIO.read(new File("src\\main\\resources\\img\\mamont_bg.png"));
            frontBear = ImageIO.read(new File("src\\main\\resources\\img\\mamont_front.png"));
        }


        Graphics bgGraphics = bufferedImage.getGraphics();


        Image bufferedStar = ImageIO.read(new File("src\\main\\resources\\img\\star.png"));

        int heightSize = 720;
        int widthSize = 1076;
        int[][] starPixel = new int[720][1076];
        long uniqueFloat = 0;
        long starCount = 0;
        Random random = new Random();
        int chance = random.nextInt(40);

        if(chance == 0)
            chance = random.nextInt(1000);


        for (int height = 0; height < heightSize; height++) {
            for (int width = 0; width < widthSize; width++) {
                if (random.nextInt(10000) < chance) {
                    starPixel[height][width] = 1;
                    bgGraphics.drawImage(bufferedStar, height-1, width-1, null);
                    starCount++;
                } else {
                    starPixel[height][width] = 0;
                }
            }
        }
        for (int i = 0; i < starPixel.length; i++) {
            for (int j = 0; j < starPixel[i].length; j++) {
                if(starPixel[i][j] == 1)
                {
                    uniqueFloat = (i + j + uniqueFloat);
                }
            }
        }
        bgGraphics.drawImage(frontBear, 0, 0, null);
        starMessage = "Количество звёзд : " + starCount;
        floatMessage = "Уникальный float 0." + uniqueFloat*(starCount);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        return is;
    }
}
