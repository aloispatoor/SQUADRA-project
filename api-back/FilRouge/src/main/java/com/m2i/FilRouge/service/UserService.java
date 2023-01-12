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
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private ChannelRepository chanRepo;

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public Optional<User> getUserById(Long id){
        return repo.findById(id);
    }

    public User addUser(User user){
        return repo.save(user);
    }

    public User addChannels(Long id, List<Long> channelIds){
        User user = repo.findById(id).get();
        List<Channel> channels = chanRepo.findAllById(channelIds);
        user.setChannels(channels);
        System.out.println("Channel added by user " + user.getUsername());
        return repo.save(user);
    }

    public void deleteUser(Long id){
        repo.findById(id).orElse(null);
        repo.deleteById(id);
    }

    public List<Channel> getUserChannels(Long id){
        return repo.findById(id).get().getChannels();
    }

    public User updateUser(User user){
        User newUser = repo.findById(user.getId()).orElse(null);
        if(newUser != null){
            newUser.setEmail(user.getEmail());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            repo.save(newUser);
        }
        return newUser;
    }
}
