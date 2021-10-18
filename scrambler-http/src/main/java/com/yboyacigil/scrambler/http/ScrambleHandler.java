package com.yboyacigil.scrambler.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.yboyacigil.scrambler.service.ScrambleService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ScrambleHandler implements HttpHandler {

    public static final String TEXT_PARAM = "text";
    private final ScrambleService scrambleService;

    public ScrambleHandler(ScrambleService scrambleService) {
        this.scrambleService = scrambleService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        val method = exchange.getRequestMethod();
        if (method.equals("GET")) {
            val query = exchange.getRequestURI().getQuery();
            if (query == null) {
                val response = String.format("Bad request no '%s' query param", TEXT_PARAM);
                sendResponse(exchange, 400, response);
                return;
            }
            Map<String, String> params = getQueryParams(query);
            if (params.containsKey(TEXT_PARAM)) {
                val text = params.get(TEXT_PARAM);
                val response = scrambleService.scramble(text);
                sendResponse(exchange, 200, response);
            } else {
                val response = String.format("Bad request no '%s' query param", TEXT_PARAM);
                sendResponse(exchange, 400, response);
            }
        } else {
            val response = String.format("Method: '%s' not supported", method);
            sendResponse(exchange, 405, response);
        }
    }

    private Map<String, String> getQueryParams(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

    private void sendResponse(HttpExchange exchange, int rCode, String response) throws IOException {
        exchange.sendResponseHeaders(rCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
