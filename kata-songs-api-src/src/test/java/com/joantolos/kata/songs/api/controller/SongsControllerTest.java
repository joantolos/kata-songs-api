package com.joantolos.kata.songs.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SongsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldPostSong() throws Exception {

        this.mockMvc
                .perform(post("/song")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Respect\", \"artist\": \"Aretha Franklin\", \"album\": \"I Never Loved a Man\", \"year\": \"1967\" }"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetSong() throws Exception {

        this.mockMvc
                .perform(get("/song?name=Yellow&artist=Coldplay"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetSongLyrics() throws Exception {

        this.mockMvc
                .perform(get("/song/lyrics"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
