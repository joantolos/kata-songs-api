package com.joantolos.kata.songs.api.remote;

import com.joantolos.kata.songs.api.domain.entity.RetrieveOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("country-songs-api")
public interface CountryAPIClient {

    @RequestMapping(method = RequestMethod.GET, value = "/song/all")
    RetrieveOutput getAllSongs();
}
