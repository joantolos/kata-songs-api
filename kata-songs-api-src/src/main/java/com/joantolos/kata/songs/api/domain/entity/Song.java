package com.joantolos.kata.songs.api.domain.entity;

public class Song {

    private String name;
    private String artist;

    public Song() {
    }

    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }
}
