package com.joantolos.kata.songs.api.domain.service;

import com.joantolos.kata.songs.api.domain.dao.SongDAO;
import com.joantolos.kata.songs.api.domain.entity.RetrieveOutput;
import com.joantolos.kata.songs.api.domain.entity.Song;
import com.joantolos.kata.songs.api.remote.CountryAPIClient;
import com.joantolos.kata.songs.api.remote.LyricsAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class SongService {

    @Autowired
    private SongDAO songDAO;

    @Autowired
    private LyricsAPI lyricsAPI;

    @Autowired
    private CountryAPIClient countryAPI;

    public RetrieveOutput getSong(String name, String artist) throws SQLException {
        return new RetrieveOutput(songDAO.retrieveSong(name, artist));
    }

    public boolean updateSong(Song inputSong) throws SQLException {
        return songDAO.updateSong(inputSong.getName(), inputSong.getArtist(), inputSong.getAlbum(), inputSong.getYear());
    }

    public RetrieveOutput getAllSongs() throws SQLException {
        List<Song> allSongs = songDAO.retrieveSong("", "");
        List<Song> allRemoteSongs = countryAPI.getAllSongs();
        allSongs.addAll(allRemoteSongs);
        return new RetrieveOutput(allSongs);
    }

    public RetrieveOutput getAllSongsLyrics() throws SQLException {
        List<Song> allSongs = songDAO.retrieveSong("", "");
        allSongs.forEach(song -> song.setLyrics(this.lyricsAPI.getLyric(song.getName(), song.getArtist())));
        return new RetrieveOutput(allSongs);
    }

    public boolean addSong(Song inputSong) throws SQLException {
        return songDAO.addSong(inputSong.getName(), inputSong.getArtist(), inputSong.getAlbum(), inputSong.getYear());
    }
}