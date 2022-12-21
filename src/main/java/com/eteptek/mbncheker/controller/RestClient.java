package com.eteptek.mbncheker.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eteptek.mbncheker.rest.model.MobileNumber;

@Component
@Controller
public class RestClient {

  @Value("${rapidapi.phone.url}")
  String covidUrl;

  @Value("${rapidapi.key.name}")
  String apiKeyName;

  @Value("${rapidapi.key.value}")
  String apiKeyValue;

  @Value("${rapidapi.host.name}")
  String hostName;

  @Value("${rapidapi.host.phone.value}")
  String hostValue;
  
  RestTemplate restTemplate;

  public RestClient(RestTemplateBuilder restTemplateBuilder) {
      restTemplate = restTemplateBuilder.build();
  }
  
  @GetMapping("/validate")
  public ResponseEntity<MobileNumber> getTotals() {
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
      ResponseEntity<MobileNumber> totalEntity = restTemplate.exchange(uri, HttpMethod.GET, request, MobileNumber.class);
      total = totalEntity.getBody();
      
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return new ResponseEntity<MobileNumber>(total, HttpStatus.OK);
  }
}