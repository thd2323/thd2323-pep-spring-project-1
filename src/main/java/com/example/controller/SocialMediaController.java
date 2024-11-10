package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.repository.AccountRepository;
import java.util.List;
import java.util.ArrayList;


 @RestController
public class SocialMediaController {
    

    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    

   MessageService messageService;
    AccountService accountService;

    //get all messages
    @GetMapping("messages")
    public ResponseEntity<List<Message>> allMessages(){
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }
    
    @PostMapping("messages")
    public ResponseEntity<Message> addMessage(
        @RequestBody Message message) {
        message = messageService.persistMessage(message);
        if(message == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(message);
    }
    
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId){
        Message m = messageService.findById(messageId);
        if(m == null){
            return ResponseEntity.status(200).body(null);
        }
        return ResponseEntity.status(200).body(m);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        System.out.println("peanut");
        Message m = messageService.findById(messageId);
        if(m == null){
            return ResponseEntity.status(200).body(null);
        }
        messageService.deleteMessage(messageId);
        return ResponseEntity.status(200).body(1);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesbyUser(@PathVariable int accountId){
        
        System.out.println("peanut");
        System.out.println("accountid is " + accountId);
        ArrayList<Message> m = (ArrayList<Message>)messageService.getAllMessagesByUser(accountId);
        if(m == null || m.isEmpty() || m.size() == 0){
            //return ResponseEntity.status(200).body(null);
            return ResponseEntity.status(200).body(m);
        }
        
        return ResponseEntity.status(200).body(m);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@RequestBody Message message, @PathVariable int messageId){
        String messageText = message.getMessageText();
        System.out.println("messageText is " + messageText);
        System.out.println("messageText length is " + messageText.length());
        
        Message m = messageService.updateMessage(messageText, messageId);
        if(m == null || messageText.isBlank() || messageText.length() == 0){
            
            return ResponseEntity.status(400).body(0);
        }
        return ResponseEntity.status(200).body(1);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> userLogin(@RequestBody Account account){
        account = accountService.userLogin(account);
        if(account == null){
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.status(200).body(account);
    }

    @PostMapping("/register")
    public ResponseEntity<Account> userRegistration(@RequestBody Account account){
        System.out.println("username of trying to register is " + account.getUsername());
        
        
        if(accountService.findByUsername(account.getUsername())){
            System.out.println("username already exists");
            return ResponseEntity.status(409).body(null);
        }
        account = accountService.userRegistration(account);
        if(account == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(account);
    }

}
