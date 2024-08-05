package com.keons.Auth.Domain.Dto;


import com.keons.Auth.Domain.Constants.Security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {

    @NotNull(message = "El campo nombre es requerido")
    private String username;

    @Email
    private String email;

    @NotNull(message = "El campo constraseña es requerido")
    private String password;

    @NotNull(message = "El campo confirmar contraseña es requerido")
    private String confirmPassword;

    @NotNull(message = "El campo numero de documento es requerido")
    private String documentNumber;

    @NotNull(message = "El campo dirección es requerido")
    private String address;

    private Role role;
}
