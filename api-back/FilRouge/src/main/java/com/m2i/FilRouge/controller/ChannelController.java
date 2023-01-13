package com.m2i.FilRouge.controller;

import com.m2i.FilRouge.entity.Channel;
import com.m2i.FilRouge.entity.Message;
import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.service.ChannelService;
import com.m2i.FilRouge.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/channels")
public class ChannelController {
    @Autowired
    private ChannelService service;
    @Autowired
    private MessageService mService;

    @GetMapping("")
    public List<Channel> getAll(){
        return service.getAllChannels();
    }

    @GetMapping("/{id}/messages")
    public List<Message> getAllMessagesByChannel(@PathVariable Long id){
        Channel channel = service.getChannelById(id).get();
        return service.getAllMessagesByChannel(channel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> getById(@PathVariable Long id){
        return service.getChannelById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Channel post(@RequestBody Channel channel){
        return service.addChannel(channel);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Channel> update(@PathVariable Long id, @RequestBody Channel channel){
        return service.getChannelById(id)
                .map(savedChannel -> {
                    savedChannel.setName(channel.getName());
                    savedChannel.setDescription(channel.getDescription());
                    savedChannel.setMessages(channel.getMessages());

                    Channel newChannel = null;
                    try {
                        newChannel = service.updateChannel(savedChannel);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return new ResponseEntity<>(newChannel, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        service.deleteChannel(id);
        return new ResponseEntity<String>("Channel deleted successfully", HttpStatus.OK);
    }
}
