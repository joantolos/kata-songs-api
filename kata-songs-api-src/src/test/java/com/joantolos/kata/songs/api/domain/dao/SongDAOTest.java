package com.joantolos.kata.songs.api.domain.dao;

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
public class SongDAOTest {

    @Autowired
    private SongDAO songDAO;

    @Test
    public void shouldRetrieveSong() throws SQLException {
        Song expectedSong = new Song("Yellow", "Coldplay", "Parachutes", "2000");
        Song actualSong = songDAO.retrieveSong("Yellow", "Coldplay").get(0);
        Assert.assertEquals(expectedSong.getAlbum(), actualSong.getAlbum());
    }
}
