Feature: Song

  @Retrieve
  Scenario: Retrieving a song
    Given I perform a "GET" to "http://localhost:7111/song?name=Yellow&artist=Coldplay" end point
    Then the response at the json path "songs[0].album" includes "Parachutes"

  @Retrieve.All.Songs
  Scenario: Retrieving all songs
    Given I perform a "GET" to "http://localhost:7111/song/all" end point
    Then the response at the json path "songs[0].album" includes "Parachutes"

  @POST.Song
  Scenario: Post a song
    Given I use body from file "payloads/respect.json"
    When I perform a "POST" to "http://localhost:7111/song" end point
    Then the response status is 201

  @PUT.Song
  Scenario: Update a song
    Given I use body from file "payloads/respectGreatestHits.json"
    When I perform a "PUT" to "http://localhost:7111/song" end point
    Then the response status is 204