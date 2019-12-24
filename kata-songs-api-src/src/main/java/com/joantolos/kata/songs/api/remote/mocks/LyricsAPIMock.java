package com.joantolos.kata.songs.api.remote.mocks;

import org.apache.commons.io.IOUtils;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class LyricsAPIMock {

    private ClientAndServer lyricsAPIMock;

    public LyricsAPIMock(Integer port) {
        this.lyricsAPIMock = ClientAndServer.startClientAndServer(port);
        this.configureMocks();
    }

    public void stop() {
        this.lyricsAPIMock.stop();
    }

    private void configureMocks() {
        Arrays.stream(LyricsAPIRequest.values()).forEach(request -> mockRequest(request.getPath(), request.getOutputJson()));
    }

    private void mockRequest(String path, String outputJson) {
        try {
            lyricsAPIMock.when(
                    HttpRequest.request(path)
                            .withMethod("GET")
                            .withKeepAlive(true)
                            .withSecure(false)
            ).respond(
                    HttpResponse.response()
                            .withStatusCode(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(new JsonBody(IOUtils.toString(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(outputJson)), StandardCharsets.UTF_8)))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
