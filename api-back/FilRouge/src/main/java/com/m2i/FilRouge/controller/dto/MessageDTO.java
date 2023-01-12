package com.m2i.FilRouge.controller.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class MessageDTO {
    private Long id;
    @NotBlank
    @Size(max = 500, message = "Votre message est trop long")
    private String content;
}
