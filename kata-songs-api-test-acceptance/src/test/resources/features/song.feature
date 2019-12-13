Feature: Song

  @Retrieve
  Scenario: Retrieving a song
    Given I perform a "GET" to "http://localhost:7111/song?name=Yellow&artist=Coldplay" end point
    Then the response at the json path "songs[0].album" includes "Parachutes"