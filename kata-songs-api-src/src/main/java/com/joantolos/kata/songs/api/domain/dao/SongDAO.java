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

    private void executeStatement(String statementSQL) throws SQLException {
        log.info(statementSQL);
        try (Statement statement = connection.createStatement()) {
            statement.execute(statementSQL);
        }
    }

    public List<Song> retrieveSong(String name, String artist) throws SQLException {
        String getQuery = "SELECT s.name, s.artist, s.album, s.release_year FROM songs s WHERE s.name = '" + name + "'" + " AND s.artist = '" + artist + "'";

        List<Song> songs = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(getQuery);

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
