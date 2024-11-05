package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.repository.MessageRepository;
import java.util.List;


 @RestController
public class SocialMediaController {
    

    public SocialMediaController(MessageService messageService){
        this.messageService = messageService;
    }

    

   MessageService messageService;

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

}
