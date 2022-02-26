package com.example.webapp.telegram.gather.modules;

import com.example.webapp.telegram.gather.impl.ModuleImpl;
import com.example.webapp.utils.ParserUtils;
import org.jsoup.nodes.Document;

public class EightSotSu extends ModuleImpl {

    public EightSotSu(String status, String name) {
        super(status, name);
    }

    @Override
    public String getInfo(String number) {
        StringBuilder info = new StringBuilder();

        char[] numberArray = number.toCharArray();
        Document doc = null;

        try {
            doc = ParserUtils.getPage("https://8sot.su/ru/codes" + numberArray[0] + "/" + numberArray[1] + numberArray[2] + numberArray[3]
                + "/" + numberArray[4] + numberArray[5] + numberArray[6] + numberArray[7] + numberArray[8] + numberArray[9] + numberArray[10]);
         } catch (Exception e) {
            onError(e.getMessage());
        }
        String region = doc.select("div.ptb__region").text();
        String dsrc = doc.select("div.ptb__region_dscr").text();
        String operater = doc.select("div.ptb__opname_dscr").text();

        info.append("Region : ").append(region).append("\n");
        info.append("Region description : ").append(dsrc).append("\n");
        info.append("Operator : ").append(operater).append("\n");

        return info.toString();
    }

    @Override
    public void onError(String errorMessage) {

    }
}
