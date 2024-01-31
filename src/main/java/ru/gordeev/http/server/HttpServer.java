package ru.gordeev.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private final Logger logger;
    private final ExecutorService threadPool;
    private final int port;
    private final Dispatcher dispatcher;

    public HttpServer(int port) {
        this.logger = LogManager.getLogger(HttpServer.class.getName());

        this.port = port;
        this.dispatcher = new Dispatcher();
        this.threadPool = Executors.newFixedThreadPool(8);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Server has been started on port {}", port);
            while (true) {
                threadPool.execute(() -> {
                    try (Socket socket = serverSocket.accept()) {
                        byte[] buffer = new byte[8192];
                        int n = socket.getInputStream().read(buffer);
                        if (n > 0) {
                            HttpRequest httpRequest = new HttpRequest(new String(buffer, 0, n));
                            dispatcher.execute(httpRequest, socket.getOutputStream());
                        }
                    } catch (IOException e) {
                        logger.error(e);
                    }
                });
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
