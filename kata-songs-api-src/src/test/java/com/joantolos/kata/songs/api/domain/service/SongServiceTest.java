package com.joantolos.kata.songs.api.domain.service;

import com.joantolos.kata.songs.api.domain.entity.Song;
import com.joantolos.kata.songs.api.remote.mocks.LyricsAPIMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SongServiceTest {

    private LyricsAPIMock lyricsAPIMock;

    @Before
    public void setUp() {
        lyricsAPIMock = new LyricsAPIMock(7201);
    }

    @After
    public void tearDown() {
        lyricsAPIMock.stop();
    }

    @Autowired
    private SongService songService;

    @Test
    public void shouldGetSong() throws SQLException {
        Song expectedSong = new Song("Yellow", "Coldplay", "Parachutes", "2000");
        Song actualSong = songService.getSong("Yellow", "Coldplay").getSongs().get(0);
        Assert.assertEquals(expectedSong.getAlbum(), actualSong.getAlbum());
    }

    @Test
    public void shouldGetLyrics() throws SQLException {
        Song yellow = songService.getAllSongsLyrics().getSongs().stream().filter(song -> song.getName().equals("Yellow")).findAny().get();
        Assert.assertTrue(yellow.getLyrics().startsWith("Fake lyrics for Yellow"));
    }
}
