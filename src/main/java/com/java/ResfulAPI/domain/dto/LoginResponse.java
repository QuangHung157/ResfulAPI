package com.java.ResfulAPI.domain.dto;

public class LoginResponse {
    private String message;
    private String accessToken;

    public LoginResponse() {
    }

    public LoginResponse(String message, String accessToken) {
        this.message = message;
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
