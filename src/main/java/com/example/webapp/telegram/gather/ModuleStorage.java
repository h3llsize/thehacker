package com.example.webapp.telegram.gather;

import com.example.webapp.telegram.gather.impl.ModuleImpl;
import com.example.webapp.telegram.gather.modules.EightSotSu;
import com.example.webapp.telegram.impl.MessageImpl;

import java.util.HashMap;

public class ModuleStorage {
    public static final HashMap<String, ModuleImpl> numberFindMap = new HashMap<String, ModuleImpl>();
    static {
        new EightSotSu("number","8sot.su");
    }
}
