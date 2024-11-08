package com.example.service;

import com.example.entity.Account;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {
    
    
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    /* 
     * 
     * 
    */

    public Message persistMessage(Message message){
        String text = message.getMessageText();
        int postedBy = message.getPostedBy();
        
        Optional<Account> optionalAccount = accountRepository.findById(postedBy);
        

        if(text.isBlank() || text.length() > 255 || !optionalAccount.isPresent()){
            return null;
        }
        return messageRepository.save(message);
    }

    public Message findById(int messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(!optionalMessage.isPresent()){
            return null;
        }
        Message m = optionalMessage.get();
        return m;
    }

    public void deleteMessage(int id){
        messageRepository.deleteById(id);

    }

    public List<Message> getAllMessagesByUser(int accountId){
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(!optionalAccount.isPresent()){
            return null;
        }
        return messageRepository.findMessageByPostedBy(accountId);

    }

    public Message updateMessage(String text, int messageId){
        System.out.println("service text is " + text);
        Message m = this.findById(messageId);
        if(m == null || text.isBlank() || text.length() == 0 || text.length() > 255){
            return null;
        }
        m.setMessageText(text);
        messageRepository.save(m);
        return m;

    } 

}
