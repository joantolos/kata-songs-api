package com.joantolos.kata.songs.api.domain.service;

import com.joantolos.kata.songs.api.domain.entity.Song;
import org.junit.Assert;
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

    @Autowired
    private SongService songService;

    @Test
    public void shouldGetSong() throws SQLException {
        Song expectedSong = new Song("Yellow", "Coldplay", "Parachutes", "2000");
        Song actualSong = songService.getSong("Yellow", "Coldplay").getSongs().get(0);
        Assert.assertEquals(expectedSong.getAlbum(), actualSong.getAlbum());
    }
}
