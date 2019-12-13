package com.joantolos.kata.songs.api.domain.service;

import com.joantolos.kata.songs.api.domain.entity.Song;
import org.springframework.stereotype.Component;

@Component
public class SongsService {

    public String getSong(String name, String artist) {
        return "My song";
    }

    public void postSong(Song songInput) {

    }

    public void putSong(Song songInput) {

    }
}
