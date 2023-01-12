package com.m2i.FilRouge.controller.dto;

import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.repository.MessageRepository;
import com.m2i.FilRouge.repository.UserRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


public class UserDTO {
    private Long id;
    @NotEmpty
    @Size(min = 8, message = "Votre mot de passe doit contenir plus de 8 caractères")
    private String password;
    @NotEmpty
    @Size(min = 3, message = "Votre pseudo doit contenir plus de 3 caractères")
    private String username;
    @Email
    @NotEmpty
    private String email;

    private User user;
    private List<MessageDTO> messages;
    private UserRepository userRepo;
    private MessageRepository messageRepo;

    public UserDTO(Long id){

    }

}
