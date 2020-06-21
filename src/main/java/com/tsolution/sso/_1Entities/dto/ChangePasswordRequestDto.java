package com.tsolution.sso._1Entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDto {
    private String password;
    private String newPassword;
    private String newPasswordConfirm;
}
