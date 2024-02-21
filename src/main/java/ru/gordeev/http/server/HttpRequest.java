package ru.gordeev.http.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequest {

    private final String rawRequest;
    private String uri;
    private String body;
    private final HttpMethod method;
    private Map<String, String> parameters;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.method = parseMethod();
        parseRequestLine();
        parseBody();
    }

    private void parseBody() {
        if (method == HttpMethod.POST) {
            List<String> lines = rawRequest.lines().toList();
            int emptyLineIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).isEmpty()) {
                    emptyLineIndex = i;
                }
            }

            if (emptyLineIndex > -1) {
                StringBuilder sb = new StringBuilder();
                for (int i = emptyLineIndex + 1; i < lines.size(); i++) {
                    sb.append(lines.get(i));
                }
                this.body = sb.toString();
            }
        }
    }

    public String getBody() {
        return body;
    }

    public String getUri() {
        return uri;
    }

    public String getRoute() {
        return method + " " + uri;
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
