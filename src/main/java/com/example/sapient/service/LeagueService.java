package com.example.sapient.service;

import com.example.sapient.model.Country;
import com.example.sapient.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LeagueService {

  @Autowired
  private RestTemplate restTemplate;

  public League[] getLeagues(String cid)
  {
    return restTemplate.getForObject
        ("https://apiv2.apifootball.com/?action=get_leagues&country_id="+cid+"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978", League[].class);

  }
}
