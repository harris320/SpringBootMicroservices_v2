package com.harris.productservice.testJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    private String url;

    private EmailParamsResponse emailParamsResponse;
}
