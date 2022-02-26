package com.example.webapp.telegram.stickers;

import jnr.ffi.annotations.In;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class StickersStorage {
    public enum Stickers
    {
        START_COMMAND("CAACAgEAAxkBAAE_7p1iBXzYO6hGPZWCpSxA0VJmRpGaowACEgAD2Ui5TvQ1d2k9mr_mIwQ"),
        ;

        public String sticker_url;

        Stickers(String sticker_url) {
            this.sticker_url = sticker_url;
        }

        String Stickers() {
            return sticker_url;
        }
    }
}
