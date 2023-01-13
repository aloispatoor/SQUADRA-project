package com.m2i.FilRouge.service;

import com.m2i.FilRouge.entity.Channel;
import com.m2i.FilRouge.entity.Message;
import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.repository.ChannelRepository;
import com.m2i.FilRouge.repository.MessageRepository;
import com.m2i.FilRouge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repo;
    @Autowired
    private ChannelRepository chanRepo;
    @Autowired
    private UserRepository userRepo;

    public List<Message> getAllMessages(){
        return repo.findAll();
    }

    public Optional<Message> getMessageById(Long id){
        return repo.findById(id);
    }

    public Message addMessageInChannel(Message message, Long id){
        Channel channel = chanRepo.findById(id).get();
        message.setChannel(channel);
        return repo.save(message);
    }

    public Message addMessageByUser(Message message, Long id){
        User user = userRepo.findById(id).get();
        message.setUser(user);
        return repo.save(message);
    }

    public void deleteMessage(Long id){
        repo.findById(id).orElse(null);
        repo.deleteById(id);
    }

    public Message updateMessage(Message message){
        Message newMessage = repo.findById(message.getId()).orElse(null);
        if(newMessage != null){
            newMessage.setContent(message.getContent());
            repo.save(newMessage);
        }
        return newMessage;
    }
}
