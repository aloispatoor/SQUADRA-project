package com.m2i.FilRouge.controller;

import com.m2i.FilRouge.entity.Message;
import com.m2i.FilRouge.service.ChannelService;
import com.m2i.FilRouge.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "")
public class MessageController {
    @Autowired
    private MessageService service;

    @GetMapping("/messages")
    public List<Message> getAll(){
        return service.getAllMessages();
    }

    @GetMapping("messages/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id){
        return service.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("channels/{id}/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public Message add(@PathVariable("id") Long channelId, @RequestBody Message message){
        return service.addMessageInChannel(message, channelId);
    }

    @DeleteMapping("messages/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteMessage(id);
        return new ResponseEntity<String>("Message removed successfully", HttpStatus.OK);
    }
}
