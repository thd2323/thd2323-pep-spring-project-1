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

    //returns true if an account with this username exists.
    //returns false otherwise
    public boolean findByUsername(String username){
        
        Account test = accountRepository.findAccountByUsername(username);
        //System.out.println("username of test is " + test.getUsername());
        if(test == null){
            return false;
        }
        return true;
    }

    public Account userRegistration(Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        
        if(username.isBlank() || password.length() < 4){
            return null;
        }

        return accountRepository.save(account);
    }
    
    

}
