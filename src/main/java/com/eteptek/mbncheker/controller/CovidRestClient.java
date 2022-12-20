package com.eteptek.mbncheker.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eteptek.mbncheker.rest.model.MobileNumber;

@Component
public class CovidRestClient {

  @Value("${rapidapi.covid.url}")
  String covidUrl;

  @Value("${rapidapi.key.name}")
  String apiKeyName;

  @Value("${rapidapi.key.value}")
  String apiKeyValue;

  @Value("${rapidapi.host.name}")
  String hostName;

  @Value("${rapidapi.host.covid.value}")
  String hostValue;
  
  RestTemplate restTemplate;

  public CovidRestClient(RestTemplateBuilder restTemplateBuilder) {
      restTemplate = restTemplateBuilder.build();
  }
  public MobileNumber getTotals() {
    MobileNumber total = null;
    try {
      URI uri;
      uri = new URI(covidUrl);
      HttpHeaders headers = new HttpHeaders();
      headers.set(apiKeyName, apiKeyValue);
      headers.set(hostName, hostValue);
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
      HttpEntity<String> request = new HttpEntity<String>(headers);
      ResponseEntity<MobileNumber[]> totalEntity = restTemplate.exchange(uri, HttpMethod.GET, request,
          MobileNumber[].class);
      total = totalEntity.getBody()[0];

    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return total;
  }
}