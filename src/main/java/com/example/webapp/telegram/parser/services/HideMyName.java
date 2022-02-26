package com.example.webapp.telegram.parser.services;

import com.example.webapp.Logger;
import com.example.webapp.telegram.parser.ServiceImpl;
import com.example.webapp.utils.ParserUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HideMyName extends ServiceImpl {
    @Override
    public void start() {
        try {
            Document document = ParserUtils.getPage("https://hidemy.name/ru/proxy-list/?country=RU&type=h#list");
            Elements elements = document.select("div.table_block").select("tbody").select("tr");
            for (Element el : elements)
            {
                String ip = el.select("td").get(0).text();
                String port = el.select("td").get(1).text();

                proxies.add(generateAuthProxy(ip,port));

            }
        } catch (IOException e) {
            Logger.sendBomber(e.toString());
        }
    }
}
