package ru.gordeev.http.server.processors;

import ru.gordeev.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    String htmlResponseTemplate = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=utf-8\r\n\r\n<html><body><h1>%s</h1></body></html>";
    String jsonResponseTemplate = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n%s";
    String unknownResponseTemplate = """
            HTTP/1.1 404 Not Found\r
            Content-Type: text/html;charset=UTF-8\r\n
            <html>
            <head><title>404 Not Found</title></head>
            <body>
            <h1>Not Found</h1>
            <p>The requested URL %s was not found on this server</p>
            </body>
            </html>""";

    void execute(HttpRequest request, OutputStream outputStream) throws IOException;
}
