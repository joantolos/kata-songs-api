package com.joantolos.kata.songs.api.controller;

import com.joantolos.kata.songs.api.domain.entity.Song;
import com.joantolos.kata.songs.api.domain.service.SongsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class SongsController {

    private final Logger log = LoggerFactory.getLogger(SongsController.class);

    @Autowired
    private SongsService songsService;

    @RequestMapping(value = "/song", method = RequestMethod.POST)
    public ResponseEntity postSong(@RequestBody Song songInput) throws SQLException {
        log.info("### POST /songs endpoint called");
        this.songsService.postSong(songInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/song", method = RequestMethod.PUT)
    public ResponseEntity putSong(@RequestBody Song songInput) throws SQLException {
        log.info("### PUT /songs endpoint called");
        this.songsService.putSong(songInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/song", method = RequestMethod.GET)
    public ResponseEntity getSong(@RequestParam("name") String name, @RequestParam("artist") String artist) throws SQLException {
        log.info("### GET /songs endpoint called for name " + name + " and artist " + artist);
        return ResponseEntity.status(HttpStatus.OK).body(this.songsService.getSong(name, artist));
    }
}
