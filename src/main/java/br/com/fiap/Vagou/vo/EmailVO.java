package br.com.fiap.Vagou.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailVO {
    @NotBlank
    @Email
    private String value;

    public EmailVO() {}

    public EmailVO(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public boolean isValid() {
        return value != null && value.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}