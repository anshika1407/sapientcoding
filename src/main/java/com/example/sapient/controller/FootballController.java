package com.example.sapient.controller;

import com.example.sapient.model.*;
import com.example.sapient.service.CountryService;
import com.example.sapient.service.LeagueService;
import com.example.sapient.service.StandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class FootballController {

    private CountryService countryService;
    private LeagueService leagueService;
    private StandingService standingService;

    @Autowired
    public FootballController(CountryService countryService, LeagueService leagueService, StandingService standingService)
    {

        this.countryService = countryService;
        this.leagueService = leagueService;
        this.standingService = standingService;
    }

    @GetMapping(path="/country/{cname}/league/{lname}/team/{tname}")
    public Output getTeamStandingInaLeague(@PathVariable("cname")String countryName,
                                                 @PathVariable("lname")String leagueName,
                                                 @PathVariable("tname")String teamName)
    {
        Output output = null;
        Country[] countries = countryService.getCountries();
        String cid = Arrays.asList(countries).stream().filter(c -> c.getCountry_name().equalsIgnoreCase(countryName)).map(x -> x.getCountry_id()).findFirst().orElse(null);
        if(cid!= null)
        {
            League[] leagues =
                    leagueService.getLeagues(cid);
            String lid = Arrays.asList(leagues).stream().filter(l -> l.getLeague_name().equalsIgnoreCase(leagueName) &&
                    l.getCountry_name().equalsIgnoreCase(countryName)).map(x-> x.getLeague_id()).findFirst().orElse(null);

            if(lid!= null)
            {
                Standing[] standings  = standingService.getStandings(lid);
                Standing standing = Arrays.asList(standings).stream().filter(l -> l.getLeague_name().equalsIgnoreCase(leagueName) &&
                        l.getCountry_name().equalsIgnoreCase(countryName) && l.getTeam_name().equalsIgnoreCase(teamName)).findFirst().orElse(null);
                if(standing != null)
                {
                    output = new Output(cid, standing.getCountry_name(),
                            standing.getLeague_id(), standing.getLeague_name(),
                            standing.getTeam_id(), standing.getTeam_name(),
                            standing.getOverall_league_position());
                }
                else
                    output = new Output();
            }

        }
        return output;
    }
}
