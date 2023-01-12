package com.m2i.FilRouge.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class ChannelDTO {
    private Long id;
    @Size(max = 250, message = "Votre description est trop longue")
    private String description;
    @NotEmpty
    @Size(max = 50, message = "Le nom de votre canal est trop long")
    private String name;
}
