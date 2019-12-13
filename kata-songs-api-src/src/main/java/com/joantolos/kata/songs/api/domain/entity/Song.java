package com.joantolos.kata.songs.api.domain.entity;

public class Song {

    private String name;
    private String artist;
    private String album;
    private String year;

    public Song() {
    }

    public Song(String name, String artist, String album, String year) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }
}
