package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class BotClient extends Client {

    public static void main(String[] args) {
        new BotClient().run();

    }

    public class BotSocketThread extends SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            String userName ="";
            String text ="";
            if(message.contains(":")) {
                String[] lines = message.split(":");
                userName = lines[0].trim();
                text = lines[1].trim();
            }
            SimpleDateFormat dateFormat;
            Map<String, String> wordsMap = new HashMap<>();
            wordsMap.put("дата", "d.MM.YYYY");
            wordsMap.put("день", "d");
            wordsMap.put("месяц", "MMMM");
            wordsMap.put("год", "YYYY");
            wordsMap.put("время", "H:mm:ss");
            wordsMap.put("час", "H");
            wordsMap.put("минуты", "m");
            wordsMap.put("секунды", "s");
            wordsMap.put("сколько Славику лет?", "35");

            for(Map.Entry<String, String> entry : wordsMap.entrySet()){
                if(text.equals(entry.getKey())){
                    if(entry.getKey().equals("сколько Славику лет?")){
                        sendTextMessage(entry.getValue());
                    }else {
                        dateFormat = new SimpleDateFormat(entry.getValue());
                        sendTextMessage("Информация для " + userName + ": " + dateFormat.format(Calendar.getInstance().getTime()));
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    protected String getUserName() {
        int x = (int)(Math.random()*100);
        return "date_bot_"+x;
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }
}
