package com.m2i.FilRouge.service;

import com.m2i.FilRouge.entity.Channel;
import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.repository.ChannelRepository;
import com.m2i.FilRouge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository repo;
    @Autowired
    private UserRepository userRepo;

    public List<Channel> getAllChannels(){
        return repo.findAll();
    }

    public Channel setGeneralChannel(){
        String name = "Général";
        String description = "Canal général";
        Channel general = new Channel(name, description);
        return repo.save(general);
    }

    public Optional<Channel> getChannelById(Long id){
        return repo.findById(id);
    }

    public Channel addChannel(Channel channel){
        return repo.save(channel);
    }

    public List<User> getChannelUsers(Long id){
        return repo.findById(id).get().getUsers();
    }

    public Channel addUsers(Long id, List<Long> userIds){
        Channel channel = repo.findById(id).get();
        List<User> users = userRepo.findAllById(userIds);
        channel.setUsers(users);
        return repo.save(channel);
    }

    public void deleteChannel(Long id){
        try{
            Channel channel = repo.findById(id).get();
            if(!channel.getName().equals("Général")){
                repo.deleteById(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Channel updateChannel(Channel channel){
        try{
            Channel newChannel = repo.findById(channel.getId()).orElse(null);
            if(newChannel != null && !channel.getName().equals("Général")){
                newChannel.setName(channel.getName());
                newChannel.setDescription(channel.getDescription());
                repo.save(newChannel);
            }
            return newChannel;
        }catch (Exception e){
            e.printStackTrace();
            return channel;
        }
    }
}
