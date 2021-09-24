package com.srum.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author ThoNN1
 */
public class ChangePasswordDTO {

    @NotBlank(message = "Account is required")
    private String account;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password at least 8 characters")
    private String password;

    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "New password at least 8 characters")
    private String newPassword;

    public ChangePasswordDTO() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
}
