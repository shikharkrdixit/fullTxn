package com.shikhar.employeeManagement.controller;


import com.shikhar.employeeManagement.entity.TransactionEntity;
import com.shikhar.employeeManagement.service.transactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final transactionService transactionServ;

    public TransactionController(transactionService transactionServ) {
        this.transactionServ = transactionServ;
    }

    @GetMapping("/all")
    public List<TransactionEntity> getAll(){
        return transactionServ.getAll();
    }

    @PostMapping("/create")
    public TransactionEntity create(@RequestBody TransactionEntity transactionEntity){
        return transactionServ.createTransaction(transactionEntity);
    }
}
