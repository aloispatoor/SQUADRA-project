package com.m2i.FilRouge.controller;

import com.m2i.FilRouge.entity.Channel;
import com.m2i.FilRouge.entity.Message;
import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.service.MessageService;
import com.m2i.FilRouge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private MessageService mService;

    @GetMapping("")
    public List<User> getAll(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/messages")
    public List<Message> getAllUserMessages(@PathVariable Long id){
        User user = service.getUserById(id).get();
        return user.getMessages();
    }

    @PostMapping("/{id}/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public Message postMessage(@PathVariable Long id, @RequestBody Message message){
        return mService.addMessageByUser(message, id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User post(@RequestBody User user){
        return service.addUser(user);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        return service.getUserById(id)
                .map(savedUser -> {
                    savedUser.setEmail(user.getEmail());
                    savedUser.setUsername(user.getUsername());
                    savedUser.setPassword(user.getPassword());
                    User newUser = service.updateUser(savedUser);
                    return new ResponseEntity<>(newUser, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteUser(id);
        return new ResponseEntity<String>("User removed successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}/channels")
    public List<Channel> getUserChannels(@PathVariable Long id){
        return service.getUserChannels(id);
    }

    @PostMapping("/{id}/channels")
    @ResponseStatus(HttpStatus.CREATED)
    public Channel addChannels(@PathVariable("id") Long id, @RequestBody Channel channel){
        return service.addChannels(id, channel);
    }
}
