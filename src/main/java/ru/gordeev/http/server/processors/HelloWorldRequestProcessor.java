package ru.gordeev.http.server.processors;

import ru.gordeev.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HelloWorldRequestProcessor implements RequestProcessor {

    @Override
    public void execute(HttpRequest request, OutputStream outputStream) throws IOException {
        outputStream.write(String.format(htmlResponseTemplate, "Hello world!").getBytes(StandardCharsets.UTF_8));
    }
}
