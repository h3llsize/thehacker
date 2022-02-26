package com.example.webapp.telegram.gather;

import com.example.webapp.telegram.gather.impl.ModuleImpl;

public class GatherHandler {
    public static String getNumberInfo(String number)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (ModuleImpl module : ModuleStorage.numberFindMap.values())
        {
            stringBuilder.append(module.getInfo(number));
        }

        return stringBuilder.toString();
    }
}
