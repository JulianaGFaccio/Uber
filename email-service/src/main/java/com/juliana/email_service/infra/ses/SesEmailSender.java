package com.juliana.email_service.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.juliana.email_service.adapters.EmailSenderGateway;
import com.juliana.email_service.application.EmailSenderService;
import com.juliana.email_service.core.exeptions.EmailServiceExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("julianagarciaff5@gmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body))));
        try{
            this.amazonSimpleEmailService.sendEmail(request);
        }
        catch (AmazonServiceException ex){
            throw  new EmailServiceExeption("Faiulure while email" , ex);
        }
    }
}
