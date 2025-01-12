package com.example.loginauthapi.dto;

import java.time.LocalDateTime;

public class AlbumRequestDTO {

    private Integer id; 
    private String title;
    private String artist;
    private String condition;
    private String format;
    private String genre;
    private Integer utilizadorId;  // User ID, to map to the User entity
    private String albumSpotifyID;  // Spotify ID of the album
    private String imageURL;        // URL for the album image
    private LocalDateTime album_added_date = LocalDateTime.now(); // Date when the album was added (field name as per your request)

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(Integer utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    public String getAlbumSpotifyID() {
        return albumSpotifyID;
    }

    public void setAlbumSpotifyID(String albumSpotifyID) {
        this.albumSpotifyID = albumSpotifyID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDateTime getAlbum_added_date() {
        return album_added_date;
    }

    public void setAlbum_added_date(LocalDateTime album_added_date) {
        this.album_added_date = album_added_date;
    }

}
