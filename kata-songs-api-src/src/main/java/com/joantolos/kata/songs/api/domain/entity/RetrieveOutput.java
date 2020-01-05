package com.joantolos.kata.songs.api.domain.entity;

import java.util.List;

public class RetrieveOutput {

    public List<Song> songs;

    public RetrieveOutput() {
    }

    public RetrieveOutput(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
