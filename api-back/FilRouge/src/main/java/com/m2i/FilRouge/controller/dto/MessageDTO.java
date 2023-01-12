package com.m2i.FilRouge.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDTO {
    private Long id;
    @NotBlank
    @Size(max = 500, message = "Votre message est trop long")
    private String content;
}
