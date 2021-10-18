package com.yboyacigil.scrambler.http;

import com.sun.net.httpserver.HttpServer;
import com.yboyacigil.scrambler.service.ScrambleService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ServiceLoader;

@Slf4j
public class Main {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        log.info("---- Scrambler Http ----");

        val serviceLoader = ServiceLoader.load(ScrambleService.class);
        val scrambleServiceOptional = serviceLoader.findFirst();
        scrambleServiceOptional.ifPresentOrElse(
            scrambleService -> {
                try {
                    val server = HttpServer.create(new InetSocketAddress(PORT), 0);
                    server.createContext("/scramble", new ScrambleHandler(scrambleService));
                    server.setExecutor(null); // creates a default executor
                    server.start();
                    log.info("Server started at port: {}", PORT);
                } catch (IOException e) {
                    log.error("Error in server", e);
                }
            },
            () -> {
                throw new IllegalStateException("No scramble service found");
            }
        );
    }

}
