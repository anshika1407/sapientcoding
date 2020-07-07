package com.example.sapient.controller;

import com.example.sapient.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class FootballController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path="/country/{cname}/league/{lname}/team/{tname}")
    public Output getTeamStandingInaLeague(@PathVariable("cname")String countryName,
                                                 @PathVariable("lname")String leagueName,
                                                 @PathVariable("tname")String teamName)
    {
        Output output = null;
        Country[] countries = restTemplate.getForObject
                ("https://apiv2.apifootball.com/?action=get_countries&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978", Country[].class);
        String cid = Arrays.asList(countries).stream().filter(c -> c.getCountry_name().equalsIgnoreCase(countryName)).map(x -> x.getCountry_id()).findFirst().orElse(null);
        if(cid!= null)
        {
            League[] leagues =
                    restTemplate.getForObject
                            ("https://apiv2.apifootball.com/?action=get_leagues&country_id="+cid+"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978", League[].class);

            String lid = Arrays.asList(leagues).stream().filter(l -> l.getLeague_name().equalsIgnoreCase(leagueName) &&
                    l.getCountry_name().equalsIgnoreCase(countryName)).map(x-> x.getLeague_id()).findFirst().orElse(null);

            if(lid!= null)
            {
                Standing[] standings  = restTemplate.getForObject(
                        "https://apiv2.apifootball.com/?action=get_standings&league_id="+lid+"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978",
                        Standing[].class
                );
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
