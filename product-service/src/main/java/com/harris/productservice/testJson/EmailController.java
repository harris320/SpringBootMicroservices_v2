package com.harris.productservice.testJson;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @PostMapping("/params")
    public EmailRequest emailParams(@RequestBody EmailParams emailParams){
        EmailParamsResponse response = new EmailParamsResponse();
        response.setTo(emailParams.getTo());
        response.setFrom(emailParams.getFrom());
        response.addProperties("dynamicField - subject2","Congrats!!");
        response.addProperties("dynamicField - result2","Approved new card");
        response.addProperties("dynamicField - caseNum","CTX824");

        EmailRequest request = new EmailRequest();
        request.setUrl("/sendEmail");
        request.setEmailParamsResponse(response);

        return request;
    }
}
