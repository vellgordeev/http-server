package ru.gordeev.http.server.processors;

import com.google.gson.Gson;
import ru.gordeev.http.server.HttpRequest;
import ru.gordeev.http.server.entities.Product;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PostBodyDemoRequestProcessor implements RequestProcessor {
    private Gson gson;

    public PostBodyDemoRequestProcessor() {
        this.gson = new Gson();
    }

    @Override
    public void execute(HttpRequest request, OutputStream outputStream) throws IOException {
        Product product = gson.fromJson(request.getBody(), Product.class);
        System.out.println(product);

        outputStream.write(String.format(responseTemplate, "").getBytes(StandardCharsets.UTF_8));
    }
}
