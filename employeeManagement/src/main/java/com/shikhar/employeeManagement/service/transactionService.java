package com.shikhar.employeeManagement.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shikhar.employeeManagement.entity.TransactionEntity;
import com.shikhar.employeeManagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class transactionService {

    ObjectMapper ObjMap = new ObjectMapper();

    private final TransactionRepository transactionRepository;

    //@Value("${transaction.topic}")
    private static final String TOPIC="transactionTopic1";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public transactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionEntity> getAll(){
        return transactionRepository.findAll();
    }

    public TransactionEntity createTransaction(TransactionEntity transactionEntity) {
        transactionRepository.save(transactionEntity);
        //String message = ObjMap.writeValueAsString(transactionEntity);
        String message = null;
        //message = transactionEntity.toString();
        try {
            message = ObjMap.writeValueAsString(transactionEntity);
        } catch (JsonProcessingException e) {
        }
        kafkaTemplate.send(TOPIC,message);
        return transactionEntity;
    }
}
