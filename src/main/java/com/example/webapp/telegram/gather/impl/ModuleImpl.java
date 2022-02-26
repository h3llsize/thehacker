package com.example.webapp.telegram.gather.impl;

import com.example.webapp.telegram.gather.ModuleStorage;
import com.example.webapp.telegram.impl.ErrorHandler;

public abstract class ModuleImpl implements ErrorHandler {
    private final String status;
    private final String name;

    public ModuleImpl(String status, String name)
    {
        this.status = status;
        this.name = name;

        init();
    }

    private void init() {
        if(status.equals("number"))
        {
            ModuleStorage.numberFindMap.put(name, this);
        }
    }

    public abstract String getInfo(String number);
}
