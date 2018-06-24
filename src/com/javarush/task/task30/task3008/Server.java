package com.javarush.task.task30.task3008;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        ConsoleHelper.writeMessage("Введите порт:");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            System.out.println("Сервер запущен");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch(Exception e){
            System.out.println("Ошибка");
        }
    }

    public static void sendBroadcastMessage(Message message){
        for(Map.Entry<String, Connection> entry : connectionMap.entrySet()){
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Сообщение не отправлено");
            }
        }
    }
        
    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection)throws IOException, ClassNotFoundException{
            String name = "";
            Message sendMessage = new Message(MessageType.NAME_REQUEST);
            while(true){
                connection.send(sendMessage);
                Message receivedMessage = connection.receive();
                name = receivedMessage.getData();
                if(receivedMessage.getType().equals(MessageType.USER_NAME)){
                    if(!name.isEmpty()){
                        if(!connectionMap.containsKey(name)){
                            connectionMap.put(name, connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            break;
                        }
                    }
                }else
                    continue;
            }
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(Map.Entry<String, Connection> entry : connectionMap.entrySet()){
                if(!entry.getKey().equals(userName)){
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName)throws IOException, ClassNotFoundException{

            while(true){
                StringBuilder sBuilder = new StringBuilder(userName+": ");
                Message message = connection.receive();
                if(message.getType()==MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT, sBuilder.append(message.getData()).toString()));
                }else
                    ConsoleHelper.writeMessage("Error!");
            }
        }

        public void run(){
            String userName = "";
            try {
                ConsoleHelper.writeMessage("Установленно новое соединение с " +socket.getRemoteSocketAddress());
                Connection connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection,userName);
                serverMainLoop(connection, userName);
            } catch(IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage(userName+" покинул чат");
            }
            connectionMap.remove(userName);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
        }
    }
}
