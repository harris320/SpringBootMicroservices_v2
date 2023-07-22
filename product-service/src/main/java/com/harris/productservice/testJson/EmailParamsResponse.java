package com.harris.productservice.testJson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class EmailParamsResponse {

    private Map<String,String> properties;

    public EmailParamsResponse(){
        properties = new LinkedHashMap<>();
    }

    @JsonProperty("fromEmail")
    private String from;

    @JsonProperty("toEmail")
    private String to;

    public void addProperties(String key, String val){
        properties.put(key, val);
    }

    @JsonAnyGetter
    private Map<String,String> getProperties(){
        return properties;
    }

}
