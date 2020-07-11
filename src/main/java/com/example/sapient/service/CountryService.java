package com.example.sapient.service;

import com.example.sapient.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {

  @Autowired
  private RestTemplate restTemplate;

  public Country[] getCountries()
  {
    return restTemplate.getForObject
        ("https://apiv2.apifootball.com/?action=get_countries&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978", Country[].class);

  }
}
