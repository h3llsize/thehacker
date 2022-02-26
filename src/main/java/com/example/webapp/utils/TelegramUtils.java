package com.example.webapp.utils;

import com.example.webapp.telegram.Bot;
import com.example.webapp.telegram.actions.callbacks.SendCallback;
import com.example.webapp.telegram.query.referral.BomberQuery;
import jnr.ffi.annotations.In;
import org.apache.catalina.util.IOTools;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TelegramUtils {
    public static InputFile getPhotoById(String file_id) throws IOException {
        String url = String.format("https://api.telegram.org/bot%s/getFile?file_id=%s",Bot.token,file_id);
        JSONObject jsonObject = readJsonFromUrl(url);

        System.out.println(jsonObject);

        String filePath = (String) jsonObject.getJSONObject("result").get("file_path");
        String fileUrl = String.format("https://api.telegram.org/file/bot%s/%s",Bot.token,filePath);
        System.out.println(fileUrl);

        BufferedImage bufferedImage = ImageIO.read(new URL(fileUrl));
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(bufferedImage, "png", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());


        InputFile inputFile = new InputFile();
        inputFile.setMedia(is, "post.png");

        return inputFile;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String fixMessage(String message)
    {
        String result = message.replaceAll("\\.","\\\\.").replaceAll("\\-","\\\\-")
                .replaceAll("\\)", "\\\\)").replaceAll("\\(", "\\\\(").replaceAll("\\!","\\\\!")
                .replaceAll("\\#","\\\\#").replaceAll("\\=","\\\\=").replaceAll("\\+","\\\\+")
                .replaceAll("\\>","\\\\>").replaceAll("\\<","\\\\<").replaceAll("\\{","\\\\{")
                .replaceAll("\\}","\\\\}").replaceAll("\\_","\\\\_");
        return result;
    }

    public static InputStream getInputStream(String fileID)
    {
        GetFile getFile;

        try {
            getFile = GetFile.builder().fileId(fileID).build();
        } catch (Exception e)
        {
            getFile = null;
        }

        String fileUrl;

        try {
            fileUrl = new Bot().execute(getFile).getFileUrl(Bot.token);
        } catch (TelegramApiException e) {
            fileUrl = null;
        }

        System.out.println("FILE URL : " + fileUrl);

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new URL(fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        return is;
    }

    public static List<InlineKeyboardButton> getDeclineAndSend()
    {
        List<InlineKeyboardButton> list = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Send");

        return null;
    }
}
