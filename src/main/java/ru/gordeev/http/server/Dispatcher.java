package ru.gordeev.http.server;

import ru.gordeev.http.server.processors.HelloWorldRequestProcessor;
import ru.gordeev.http.server.processors.OperationAddRequestProcessor;
import ru.gordeev.http.server.processors.RequestProcessor;
import ru.gordeev.http.server.processors.UnknownHttpRequestProcessor;

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
        this.router.put("/add", new OperationAddRequestProcessor());
        this.router.put("/", new HelloWorldRequestProcessor());
    }

    public void execute(HttpRequest request, OutputStream outputStream) throws IOException {
        if (!router.containsKey(request.getUri())) {
            unknownRequestProcessor.execute(request, outputStream);
            return;
        }
        router.get(request.getUri()).execute(request, outputStream);
    }
}
