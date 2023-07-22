package com.harris.productservice.testJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailParams {

    @JsonProperty("fromEmail")
    private String from;

    @JsonProperty("toEmail")
    private String to;

    @JsonProperty("dynamicField - subject")
    private String dynamicField1;

    @JsonProperty("dynamicField - result")
    private String dynamicField2;

    @JsonProperty("dynamicField - caseNum")
    private String dynamicField3;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDynamicField1() {
        return dynamicField1;
    }

    public void setDynamicField1(String dynamicField1) {
        this.dynamicField1 = dynamicField1;
    }

    public String getDynamicField2() {
        return dynamicField2;
    }

    public void setDynamicField2(String dynamicField2) {
        this.dynamicField2 = dynamicField2;
    }

    public String getDynamicField3() {
        return dynamicField3;
    }

    public void setDynamicField3(String dynamicField3) {
        this.dynamicField3 = dynamicField3;
    }
}
