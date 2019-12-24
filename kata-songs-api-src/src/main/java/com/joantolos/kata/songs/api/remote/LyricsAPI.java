package com.joantolos.kata.songs.api.remote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joantolos.kata.songs.api.domain.entity.LyricsOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class LyricsAPI {

    @Value("${api.lyrics.url}") private String url;

    public String getLyric(String name, String artist) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            URI uri = new URI(url + artist.replace(" ", "%20") + "/" + name.replace(" ", "%20"));
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

            return new ObjectMapper().readValue(result.getBody(), LyricsOutput.class).getLyrics();
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
