package com.example.loginauthapi.domain;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "utilizador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "user_gender")
    private String gender;

    @Column(name = "user_username")
    private String userName;

    @Column(name = "user_passwordhash")
    private String password;

    @Column(name = "user_country")
    private String country;

    @Column(name = "user_bday")
    private Date birthdate;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "user_image")
    @JsonProperty("userImage")
    private byte[] userImage;

    // OneToMany relationship with Album
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Album> albums; // This will hold the list of albums related to this user
}
