package com.tsolution.sso._1Entities.dto;

public class ChangePasswordRequestDto {
    private String password;
    private String newPassword;
    private String newPasswordConfirm;

    public ChangePasswordRequestDto(String password, String newPassword, String newPasswordConfirm) {
        this.password = password;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}
