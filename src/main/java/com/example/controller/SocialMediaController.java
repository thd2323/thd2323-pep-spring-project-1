package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    

}
