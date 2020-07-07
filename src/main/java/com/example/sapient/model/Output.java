package com.example.sapient.model;

public class Output {
    private String country_id;

    public Output(String country_id, String country_name, String league_id, String league_name, String team_id, String team_name, String ranking) {
        this.country_id = country_id;
        this.country_name = country_name;
        this.league_id = league_id;
        this.league_name = league_name;
        this.team_id = team_id;
        this.team_name = team_name;
        this.ranking = ranking;
    }

    public Output()
    {

    }

    private String country_name;
    private String league_id;

    public String getCountry_id() {
        return country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getLeague_id() {
        return league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public String getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getRanking() {
        return ranking;
    }

    private String league_name;
    private String team_id;
    private String team_name;
    private String ranking;
}
