package com.example.webapp.telegram.parser.services;

import com.example.webapp.telegram.parser.ServiceImpl;

import java.io.*;

public class FileProxy extends ServiceImpl {
    @Override
    public void start() {
        try {
            File file = new File("src/main/resources/proxies/proxy.txt");

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {

                String ip = line.split(":")[0];
                String port = line.split(":")[1];
                System.out.println(ip + " : " + port);

                proxies.add(generateAuthProxy(ip, port));

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
