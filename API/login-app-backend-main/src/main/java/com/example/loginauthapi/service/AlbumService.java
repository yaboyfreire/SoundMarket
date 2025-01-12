package com.example.loginauthapi.service;

import com.example.loginauthapi.Mapper.AlbumMapper;
import com.example.loginauthapi.domain.Album;
import com.example.loginauthapi.domain.User;
import com.example.loginauthapi.dto.AlbumRequestDTO;
import com.example.loginauthapi.repositories.AlbumRepository;
import com.example.loginauthapi.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository; // Added UserRepository as a field

    @Autowired
    public AlbumService(AlbumRepository albumRepository, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    public Album addAlbum(Album album) {
        return albumRepository.save(album);
    }

    public AlbumRequestDTO save(AlbumRequestDTO albumDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert DTO to Entity and set user explicitly
        Album album = AlbumMapper.toEntity(albumDTO);
        album.setUser(user); // Set the user

        // Explicitly set album_added_date if it's not provided in DTO
        if (album.getAlbum_added_date() == null) {
            album.setAlbum_added_date(LocalDateTime.now()); // Default to current date if not provided
        }
        
         // Log the details before saving to check if the fields are set correctly
         System.out.println("Album details before save: ");
         System.out.println("Spotify ID: " + album.getAlbum_SpotifyID());
         System.out.println("Image URL: " + album.getImageURL());
         System.out.println("Added Date: " + album.getAlbum_added_date());
        // Persist the album
        Album savedAlbum = albumRepository.save(album);

        // Return saved DTO
        return AlbumMapper.toDTO(savedAlbum);
    }

    public List<AlbumRequestDTO> findAll() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream()
                .map(AlbumMapper::toDTO) // Convert each Album to AlbumDTO
                .collect(Collectors.toList());
    }

    public Optional<AlbumRequestDTO> findById(Integer id) {
        Optional<Album> album = albumRepository.findById(id);
        return album.map(AlbumMapper::toDTO); // Convert the entity to DTO, if found
    }

    public List<AlbumRequestDTO> findByUserId(Integer userId) {
        // Fetch the albums by the given user ID
        List<Album> albums = albumRepository.findByUserId(userId);
    
        // Convert the list of Album entities to DTOs
        return albums.stream()
                .map(AlbumMapper::toDTO) // Convert each Album to AlbumDTO
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        albumRepository.deleteById(id);
    }

    // New method to search albums by name
    public List<AlbumRequestDTO> findByName(String name) {
        List<Album> albums = albumRepository.findByNameContainingIgnoreCase(name);
        return albums.stream()
                .map(AlbumMapper::toDTO) // Convert each Album to AlbumDTO
                .collect(Collectors.toList());
    }

    public List<AlbumRequestDTO> findByUser() {
        // Get the authenticated user's username from the SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the user's albums from the database
        List<Album> userAlbums = albumRepository.findByUser_UserName(username);

        // Convert the list of Album entities to DTOs
        return userAlbums.stream()
                .map(AlbumMapper::toDTO) // Convert each Album to AlbumDTO
                .collect(Collectors.toList());
    }

    public Album saveAlbum(Album album) {
        return albumRepository.save(album); // Ensure albumRepository is properly autowired
    }
}
