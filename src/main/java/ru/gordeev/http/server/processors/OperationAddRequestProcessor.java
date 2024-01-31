package ru.gordeev.http.server.processors;

import ru.gordeev.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OperationAddRequestProcessor implements RequestProcessor {

    @Override
    public void execute(HttpRequest request, OutputStream outputStream) throws IOException {
        if (!request.getParameters().isEmpty()) {
            Integer a = Integer.parseInt(request.getParameters().get("a"));
            Integer b = Integer.parseInt(request.getParameters().get("b"));
            String result = a + " + " + b + " = " + (a + b);

            outputStream.write(String.format(responseTemplate, result).getBytes(StandardCharsets.UTF_8));
        }
    }
}
