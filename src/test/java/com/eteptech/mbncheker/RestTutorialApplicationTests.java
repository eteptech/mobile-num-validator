package com.eteptech.mbncheker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.eteptek.mbncheker.rest.model.MobileNumber;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestTutorialApplicationTests {

  @Autowired
  MobileNumber covidClient;
  
  @Test
  public void testGetTotals() {
    System.out.println("Confirmed: " + covidClient.getKey1());
  }
}