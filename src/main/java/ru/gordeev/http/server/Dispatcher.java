package ru.gordeev.http.server;

import ru.gordeev.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private final Map<String, RequestProcessor> router;
    private final RequestProcessor unknownRequestProcessor;

    public Dispatcher() {
        this.router = new HashMap<>();
        this.unknownRequestProcessor = new UnknownHttpRequestProcessor();
        this.router.put("GET /add", new OperationAddRequestProcessor());
        this.router.put("GET /hello_world", new HelloWorldRequestProcessor());
        this.router.put("POST /body", new PostBodyDemoRequestProcessor());
    }

    public void execute(HttpRequest request, OutputStream outputStream) throws IOException {
        if (!router.containsKey(request.getRoute())) {
            unknownRequestProcessor.execute(request, outputStream);
            return;
        }
        router.get(request.getRoute()).execute(request, outputStream);
    }
}
