package com.eteptek.mbncheker.rest.repo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eteptek.mbncheker.rest.model.MobileNumber;
import com.eteptek.mbncheker.rest.model.PhoneModel;

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
  String str[] = new String[23];
  String list[] = new String[23];
  PhoneModel model = new PhoneModel();
  public RestClient(RestTemplateBuilder restTemplateBuilder) {
      restTemplate = restTemplateBuilder.build();
  }
  
  @GetMapping("/search")
  public String getTotals(Model m) {
    MobileNumber totals = null;
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
      totals = totalEntity.getBody();
      
      Map <?,?> entry;
      entry = totals.getAdditionalProperties();
      int i=0;
      
      for(Entry<?, ?> result: entry.entrySet()) {
    	 // System.out.println(i+"Key =: "+result.getKey()+" Value =: "+result.getValue());
    	  str[i] = result.getValue().toString();
    	  list[i] = str[i];
    	  i++;
      }
      
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
 
    showResult();
    m.addAttribute("result", model);
    return "total";
  }
  public void showResult() {
	  model.setIsValidNumber(list[0]);
	  	model.setExtension(list[1]);
	  	model.setCountryCodeSource(list[2]);
	  	model.setNumberType(list[3]);
	  	model.setInternationalFormat(list[4]);
	  	model.setRawInput(list[5]);
	  	model.setOriginalFormat(list[6]);
	  	model.setE164Format(list[7]);
	  	model.setIsValidNumberForRegion(list[8]);
	  	model.setPhoneNumberEntered(list[9]);
	  	model.setLanguageEntered(list[10]);
	  	model.setDefaultCountryEntered(list[11]);
	  	model.setCarrier(list[12]);
	  	model.setNationalNumber(list[13]);
	  	model.setIsPossibleNumber(list[14]);
	  	model.setCountryCode(list[15]);
	  	model.setPhoneNumberRegion(list[16]);
	  	model.setOutOfCountryFormatFromCH(list[17]);
	  	model.setLocation(list[18]);
	  	model.setItalianLeadingZero(list[19]);;
	  	model.setNationalFormat(list[20]);;
	  	model.setOutOfCountryFormatFromUS(list[21]);
	  	model.setTimeZone_s(list[22]);
  }
}