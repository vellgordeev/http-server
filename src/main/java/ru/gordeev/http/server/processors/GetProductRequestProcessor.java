package ru.gordeev.http.server.processors;

import com.google.gson.Gson;
import ru.gordeev.http.server.HttpRequest;
import ru.gordeev.http.server.entities.Category;
import ru.gordeev.http.server.entities.Product;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetProductRequestProcessor implements RequestProcessor{
    private Gson gson;

    public GetProductRequestProcessor() {
        this.gson = new Gson();
    }

    @Override
    public void execute(HttpRequest request, OutputStream outputStream) throws IOException {
        Product product = new Product();
        Category category = new Category();
        category.setId(1L);
        category.setTitle("FRUITS");

        product.setId(1000L);
        product.setTitle("Banana");
        product.setCategory(category);

        outputStream.write(String.format(jsonResponseTemplate, gson.toJson(product)).getBytes(StandardCharsets.UTF_8));
    }
}
