package com.keons.Users.Domain.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserDto {
    private String email;
    private String address;
}
