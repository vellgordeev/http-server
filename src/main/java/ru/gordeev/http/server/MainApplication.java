package ru.gordeev.http.server;

public class MainApplication {
    //Добавить логирование
    //Добавить обработку в параллельных запросах
    public static void main(String[] args) {
        HttpServer server = new HttpServer(Integer.parseInt((String) System.getProperties().getOrDefault("port", "8189")));

        server.start();
    }
}