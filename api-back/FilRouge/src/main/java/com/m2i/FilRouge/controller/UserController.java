package com.m2i.FilRouge.controller;

import com.m2i.FilRouge.entity.Channel;
import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("")
    public List<User> getAll(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @GetMapping("/{id}/channels")
    public List<Channel> getUserChannels(@PathVariable Long id){
        return service.getUserChannels(id);
    }

    @PostMapping("/{id}/channels")
    public User addChannels(@PathVariable Long id, @RequestBody List<Long> chanIds){
        return service.addChannels(id, chanIds);
    }

    @PostMapping("")
    public User post(@RequestBody User user){
        return service.addUser(user);
    }

    @PutMapping("/edit/{id}")
    public User update(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        return service.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.deleteUser(id);
    }
}
