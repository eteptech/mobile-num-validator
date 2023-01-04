package com.eteptek.mbncheker.rest.repo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.eteptek.mbncheker.rest.exception.AuthException;
import com.eteptek.mbncheker.rest.exception.MyErrorController;
import com.eteptek.mbncheker.rest.model.MobileNumber;
import com.eteptek.mbncheker.rest.model.PhoneModel;

@Component
@Controller
public class RestClient {
	
  String apiKeyName ="X-RapidAPI-Key";
  String apiKeyValue ="5dbd658595msh27056d181bc418ep1e2fedjsn43b74fe3c73b";
  String hostName = "X-RapidAPI-Host";
  String hostValue = "phonenumbervalidatefree.p.rapidapi.com";
  
  RestTemplate restTemplate;
  String str[] = new String[23];
  String list[] = new String[23];
  PhoneModel model = new PhoneModel();
  private MyErrorController errorController;
  public RestClient(RestTemplateBuilder restTemplateBuilder) {
      restTemplate = restTemplateBuilder.build();
  }
  
  @PostMapping("/search")
  public String getTotals(@RequestParam(name = "number") 
  	String number, @RequestParam(name = "country")
  	String country, Model m) {
	errorController.validate(country);
    MobileNumber totals = null;
    String url = "https://phonenumbervalidatefree.p.rapidapi.com/ts_PhoneNumberValidateTest.jsp?number="+number+"&country="+country;
    
    try {
      URI uri;
      uri = new URI(url);
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
       new AuthException(e.getMessage());
    }
 
    showResult();
    m.addAttribute("result", model);
    return "total";
  }
  
  @ExceptionHandler(value = NumberFormatException.class)
  public String numberformatHandler(Model theModel) {       
      theModel.addAttribute("err", "NumberFormatException");
      return "error";
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

