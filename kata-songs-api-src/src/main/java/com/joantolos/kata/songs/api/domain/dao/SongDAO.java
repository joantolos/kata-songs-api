package com.joantolos.kata.songs.api.domain.dao;

import com.joantolos.kata.songs.api.domain.entity.Song;
import com.joantolos.kata.songs.api.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SongDAO {

    private final Logger log = LoggerFactory.getLogger(SongDAO.class);

    @Value("${jdbc.driver}") private String driver;
    @Value("${jdbc.url}") private String url;
    @Value("${jdbc.user}") private String user;
    @Value("${jdbc.password}") private String password;
    @Value("${database.script}") private String databaseScript;

    @Autowired private StringUtils stringUtils;

    private Connection connection = null;

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url, user, password);

        String createDatabase = stringUtils.toString(this.getClass().getResourceAsStream("/" + databaseScript));
        this.executeStatement(createDatabase);
    }

    private boolean executeStatement(String statementSQL) throws SQLException {
        log.info(statementSQL);
        try (Statement statement = connection.createStatement()) {
            return statement.execute(statementSQL);
        }
    }

    public boolean addSong(String name, String artist, String album, String year) throws SQLException {
        return this.executeStatement("INSERT INTO songs (name, artist, album, release_year) VALUES ('" + name + "', '" + artist + "', '" + album + "', '" + year + "')");
    }

    public boolean updateSong(String name, String artist, String album, String year) throws SQLException {
        return this.executeStatement("UPDATE songs s SET s.album = '" + album + "', s.release_year = '" + year + "' WHERE s.name = '" + name + "' AND s.artist = '" + artist + "'");
    }

    public List<Song> retrieveSong(String name, String artist) throws SQLException {
        String retrieveQuery = "SELECT s.name, s.artist, s.album, s.release_year FROM songs s";
        if (!name.isEmpty() || !artist.isEmpty()) {
            return this.retrieveSong(retrieveQuery +" WHERE s.name = '" + name + "'" + " AND s.artist = '" + artist + "'");
        }
        return this.retrieveSong(retrieveQuery);
    }

    private List<Song> retrieveSong(String query) throws SQLException {
        List<Song> songs = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                songs.add(new Song(
                        String.valueOf(rs.getObject("name")),
                        String.valueOf(rs.getObject("artist")),
                        String.valueOf(rs.getObject("album")),
                        String.valueOf(rs.getObject("release_year"))));
            }
        }

        return songs;
    }
}
