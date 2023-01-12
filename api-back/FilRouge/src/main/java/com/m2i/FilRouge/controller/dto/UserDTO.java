package com.m2i.FilRouge.controller.dto;

import com.m2i.FilRouge.entity.Message;
import com.m2i.FilRouge.entity.User;
import com.m2i.FilRouge.repository.MessageRepository;
import com.m2i.FilRouge.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private List<Message> messages;
    private UserRepository userRepo;
    private MessageRepository messageRepo;

    public UserDTO(Long id){

    }

}
