package com.example.loginauthapi.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer id;

    @Column(name = "album_title")
    private String name;

    @Column(name = "album_condition")
    private String condition;

    @Column(name = "album_format")
    private String format;

    @Column(name = "album_artist")
    private String artist;

    @Column(name = "album_genre")
    private String genre;

    @Column(name = "album_SpotifyID")
    private String album_SpotifyID;

    @Column(name = "album_added_date")
    private LocalDateTime album_added_date = LocalDateTime.now();;

    @Column(name = "ImageURL")
    private String ImageURL;

    @ManyToOne(cascade = { CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH })      
    @JoinColumn(name = "album_user_id", nullable = false) // Updated column name
    @JsonBackReference 
    private User user;

}
