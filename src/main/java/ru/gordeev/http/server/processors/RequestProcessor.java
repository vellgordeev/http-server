package ru.gordeev.http.server.processors;

import ru.gordeev.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    String responseTemplate = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=utf-8\r\n\r\n<html><body><h1>%s</h1></body></html>";

    void execute(HttpRequest request, OutputStream outputStream) throws IOException;
}
