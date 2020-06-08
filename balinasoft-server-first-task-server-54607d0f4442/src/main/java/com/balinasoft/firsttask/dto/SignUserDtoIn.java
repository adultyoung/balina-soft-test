package com.balinasoft.firsttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignUserDtoIn {
    @NotBlank
    @Size(min = 4, max = 32)
    @Pattern(regexp = "[a-z0-9_\\-.@]+")
    String login;

    @NotBlank
    @Size(min = 8, max = 500)
    String password;

    public SignUserDtoIn(@JsonProperty("login")String login, @JsonProperty("password")String password) {
        this.login = login;
        this.password = password;
    }
}
