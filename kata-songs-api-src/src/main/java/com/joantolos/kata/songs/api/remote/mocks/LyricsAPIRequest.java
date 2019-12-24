package com.joantolos.kata.songs.api.remote.mocks;

public enum LyricsAPIRequest {

    YELLOW("/Coldplay/Yellow",  "lyricsAPI/lyricsYellow.json"),
    MASTER_OF_PUPPETS("/Metallica/Master%20of%20puppets",  "lyricsAPI/lyricsMasterOfPuppets.json"),
    COCAINE("/Eric%20Clapton/Cocaine",  "lyricsAPI/lyricsCocaine.json"),
    RESPECT("/Aretha%20Franklin/Respect",  "lyricsAPI/lyricsRespect.json");

    private String path;
    private String outputJson;

    LyricsAPIRequest(String path, String outputJson) {
        this.path = path;
        this.outputJson = outputJson;
    }

    public String getPath() {
        return path;
    }

    public String getOutputJson() {
        return outputJson;
    }
}