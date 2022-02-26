package com.example.webapp.telegram.actions.keyboards.list;

import com.example.webapp.telegram.actions.keyboards.KeyboardImpl;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class StartKeyboard extends KeyboardImpl {

    @Override
    protected List<KeyboardRow> generateKeyboard() {
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("\uD83E\uDDE9 Личный кабинет");
        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton1.setText("\uD83D\uDCA3 Бомбер");
        firstRow.add(keyboardButton);
        firstRow.add(keyboardButton1);

        KeyboardRow secondRow = new KeyboardRow();
        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText("\uD83D\uDC8E Лучший телеграмм канал");
        secondRow.add(keyboardButton2);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);

        return keyboardRows;
    }
}
