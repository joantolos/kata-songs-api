package com.joantolos.kata.songs.api.domain.service;

import com.joantolos.kata.songs.api.domain.dao.SongDAO;
import com.joantolos.kata.songs.api.domain.entity.RetrieveOutput;
import com.joantolos.kata.songs.api.domain.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class SongService {

    @Autowired
    private SongDAO songDAO;

    public RetrieveOutput getSong(String name, String artist) throws SQLException {
        return new RetrieveOutput(songDAO.retrieveSong(name, artist));
    }

    public void postSong(Song songInput) {

    }

    public void putSong(Song songInput) {

    }

    public RetrieveOutput getAllSongs() throws SQLException {
        return new RetrieveOutput(songDAO.retrieveSong("", ""));
    }
}
