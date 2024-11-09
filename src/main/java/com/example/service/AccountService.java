package com.example.service;

import com.example.entity.Account;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    //MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        //this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }


    public Account userLogin(Account account){
        return accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    
    

}
