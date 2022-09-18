package com.shikhar.transactionManagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shikhar.transactionManagement.Entity.EmployeeEntity;
import com.shikhar.transactionManagement.Entity.TransactionEntity;
import com.shikhar.transactionManagement.repository.EmployeeRepository;
import com.shikhar.transactionManagement.repository.TransactionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningService {


    //@Value("${transaction.topic}")
    private static final String TOPIC="transactionTopic1";

    private final EmployeeRepository employeeRepository;

    private final TransactionRepository transactionRepository;

    public ListeningService(EmployeeRepository employeeRepository, TransactionRepository transactionRepository, EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.transactionRepository = transactionRepository;
        this.emailService = emailService;
    }


    private final EmailService emailService;
    public List<EmployeeEntity> getAll(){
        return employeeRepository.findAll();
    }

    public List<TransactionEntity> getAllTrans(){
        return transactionRepository.findAll();
    }

    @KafkaListener(topics = TOPIC, groupId = "groupId")
    public void transactionListener(String message) {
        ObjectMapper ObjMap=new ObjectMapper();
        TransactionEntity transactionEntity = null;
        try{
        transactionEntity = ObjMap.readValue(message, TransactionEntity.class);}
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(transactionEntity);
        EmployeeEntity employee;
        System.out.println("User Id is: "+transactionEntity.getUserId());

        employee = employeeRepository.findById(transactionEntity.getUserId()).get();

        System.out.println("BalanceBefore "+employee.getBalance());
        System.out.println("TransactionAmountBefore "+transactionEntity.getAmount());
        System.out.println("Says hello!!!"+employee.getName());
        String mailText=null;
        String mailSub=null;
        String ccMail=transactionEntity.getEmailAdd();

        if (employee.getBalance() > transactionEntity.getAmount()) {
            employee.setBalance(employee.getBalance() - transactionEntity.getAmount());
            employeeRepository.save(employee);
            mailText = "Transaction Processed. Your Balance : "+employee.getBalance()+" after transaction of "+transactionEntity.getAmount();
            mailSub="Transaction Processed";
        } else {
            transactionEntity.setAmount(0);
            transactionRepository.save(transactionEntity);
            mailText = "Transaction Not Processed. Your Balance : "+employee.getBalance();
            mailSub="Transaction Not Processed";
        }

        //String mailText = "Your Balance : "+employee.getBalance()+" after transaction of "+transactionEntity.getAmount();
        String userMail = employee.getEmail();
        emailService.setMailSender(userMail,mailSub,mailText,ccMail);
        //if(employee.getBalance())
        System.out.println("BalanceAfter "+employee.getBalance());
    }
}
