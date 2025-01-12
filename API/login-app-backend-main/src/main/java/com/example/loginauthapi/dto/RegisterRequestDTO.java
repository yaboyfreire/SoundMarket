package com.example.loginauthapi.dto;

import java.sql.Date;

public record RegisterRequestDTO (String name, String email, String gender, String userName, String password, String country, Date birthdate, String aboutMe, byte[] Image) {
}
