package com.example.webapp.telegram.parser.services;

import com.example.webapp.Logger;
import com.example.webapp.telegram.parser.ServiceImpl;
import com.example.webapp.utils.ParserUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FreeProxyCz extends ServiceImpl {
    @Override
    public void start() {
        try {
            Document document = ParserUtils.getPage("http://free-proxy.cz/ru/proxylist/country/RU/http/ping/all");
            Elements elements = document.select("table[id=proxy_list]").select("tr");
            for (Element el : elements)
            {
                System.out.println(el.text());
            }
        } catch (IOException e) {
            Logger.sendBomber(e.toString());
        }
    }
}
