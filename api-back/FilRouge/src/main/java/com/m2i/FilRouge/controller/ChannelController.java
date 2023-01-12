package com.m2i.FilRouge.controller;

import com.m2i.FilRouge.entity.Channel;
import com.m2i.FilRouge.entity.Message;
import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.service.ChannelService;
import com.m2i.FilRouge.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "")
public class ChannelController {
    @Autowired
    private ChannelService service;
    @Autowired
    private MessageService mService;

    @GetMapping("/channels")
    public List<Channel> getAll(){
        return service.getAllChannels();
    }

    @GetMapping("channels/{id}")
    public Optional<Channel> getById(@PathVariable Long id){
        return service.getChannelById(id);
    }

//    @GetMapping("/{id}/users")
//    public List<User> getChannelUsers(@PathVariable Long id){
//        return service.getChannelUsers(id);
//    }
//
//    @PostMapping("/{id}/users")
//    public Channel addUsers(@PathVariable Long id, @RequestBody List<Long> userIds){
//        return service.addUsers(id, userIds);
//    }

    @PostMapping("/channels")
    public Channel post(@RequestBody Channel channel){
        return service.addChannel(channel);
    }
    @PostMapping("/general")
    public Channel setGeneralChannel(){
        return service.setGeneralChannel();
    }

    @PutMapping("channels/edit/{id}")
    public Channel update(@PathVariable Long id, @RequestBody Channel channel){
        channel.setId(id);
        return service.updateChannel(channel);
    }

    @DeleteMapping("channels/delete/{id}")
    public void delete(@PathVariable Long id){
        service.deleteChannel(id);
    }
}
