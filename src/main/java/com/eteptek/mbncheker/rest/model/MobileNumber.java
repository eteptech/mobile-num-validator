package com.eteptek.mbncheker.rest.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"key1",
"key2"
})
@Generated("jsonschema2pojo")
public class MobileNumber implements Serializable
{

@JsonProperty("key1")
private String key1;
@JsonProperty("key2")
private String key2;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = 3631860894272635267L;

/**
* No args constructor for use in serialization
*
*/
public MobileNumber() {
}

/**
*
* @param key1
* @param key2
*/
public MobileNumber(String key1, String key2) {
super();
this.key1 = key1;
this.key2 = key2;
}

@JsonProperty("key1")
public String getKey1() {
return key1;
}

@JsonProperty("key1")
public void setKey1(String key1) {
this.key1 = key1;
}

@JsonProperty("key2")
public String getKey2() {
return key2;
}

@JsonProperty("key2")
public void setKey2(String key2) {
this.key2 = key2;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}