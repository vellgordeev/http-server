package ru.gordeev.http.server;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private final String rawRequest;
    private String uri;
    private HttpMethod method;
    private Map<String, String> parameters;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.method = parseMethod();
        parseRequestLine();
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    private HttpMethod parseMethod() {
        int indexOfFirstSpace = rawRequest.indexOf(' ');
        return HttpMethod.valueOf(rawRequest.substring(0, indexOfFirstSpace));
    }

    private void parseRequestLine() {
        int indexOfFirstSpace = rawRequest.indexOf(' ');
        int indexOfSecondSpace = rawRequest.indexOf(' ', indexOfFirstSpace + 1);

        this.parameters = new HashMap<>();
        this.uri = rawRequest.substring(indexOfFirstSpace + 1, indexOfSecondSpace);

        if (uri.contains("?")) {
            String[] elements = uri.split("[?]");
            this.uri = elements[0];
            String[] keysValues = elements[1].split("&");
            for (String o : keysValues) {
                String[] keyValue = o.split("=");
                this.parameters.put(keyValue[0], keyValue[1]);
            }
        }
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "uri='" + uri + '\'' +
                ", method=" + method +
                ", parameters=" + parameters +
                '}';
    }
}
