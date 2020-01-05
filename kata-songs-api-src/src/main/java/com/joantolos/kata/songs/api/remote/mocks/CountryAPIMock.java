package com.joantolos.kata.songs.api.remote.mocks;

import org.apache.commons.io.IOUtils;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CountryAPIMock {

    private ClientAndServer countryAPIMock;

    public CountryAPIMock(Integer port) {
        this.countryAPIMock = ClientAndServer.startClientAndServer(port);

        try {
            countryAPIMock.when(
                    HttpRequest.request("/song/all")
                            .withMethod("GET")
                            .withKeepAlive(true)
                            .withSecure(false)
            ).respond(
                    HttpResponse.response()
                            .withStatusCode(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(new JsonBody(IOUtils.toString(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("countryAPI/countryAll.json")), StandardCharsets.UTF_8)))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.countryAPIMock.stop();
    }

}
