package com.nishant.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterRequestDto {
   private String name;
   private String username;
   private String password;
}
