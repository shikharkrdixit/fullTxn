package com.shikhar.transactionManagement.Controller;


import com.shikhar.transactionManagement.Entity.EmployeeEntity;
import com.shikhar.transactionManagement.Entity.TransactionEntity;
import com.shikhar.transactionManagement.service.EmailService;
import com.shikhar.transactionManagement.service.ListeningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactionManagement")
public class TransactionController {


    private final ListeningService listeningService;

    public TransactionController(ListeningService listeningService, EmailService emailService) {
        this.listeningService = listeningService;
        this.emailService = emailService;
    }


    @GetMapping("/getAll")
    public List<EmployeeEntity> getAll(){
        return listeningService.getAll();
    }



    @GetMapping("/allTransaction")
    public List<TransactionEntity> getAllTransaction(){
        return listeningService.getAllTrans();
    }
    private final EmailService emailService;
    @GetMapping("/send")
    public void sendMail(){
        emailService.setMailSender("sd9581@srmist.edu.in","Mail Received.","Mail Sending Successful.","sdixit362@gmail.com");
    }
}
