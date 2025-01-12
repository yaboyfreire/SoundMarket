package com.example.loginauthapi.controllers;

import com.example.loginauthapi.dto.AlbumRequestDTO;
import com.example.loginauthapi.service.AlbumService;
import com.example.loginauthapi.service.UserService;
import com.example.loginauthapi.Mapper.AlbumMapper;
import com.example.loginauthapi.domain.Album;
import com.example.loginauthapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    private final AlbumService albumService;
    private final UserService userService;

    @Autowired
    public AlbumController(AlbumService albumService, UserService userService) {
        this.albumService = albumService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.addAlbum(album);
        return new ResponseEntity<>(savedAlbum, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlbumRequestDTO>> getAllAlbums() {
        List<AlbumRequestDTO> albums = albumService.findAll();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumRequestDTO> getAlbumByAlbumId(@PathVariable Integer id) {
        Optional<AlbumRequestDTO> albumRequestDTO = albumService.findById(id);
        return albumRequestDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AlbumRequestDTO>> getAlbumsByUserId(@PathVariable Integer id) {
        List<AlbumRequestDTO> albumRequestDTOs = albumService.findByUserId(id);
        if (albumRequestDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(albumRequestDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // New endpoint to search for albums by name
    @GetMapping("/search")
    public ResponseEntity<List<AlbumRequestDTO>> getAlbumsByName(@RequestParam String name) {
        List<AlbumRequestDTO> albums = albumService.findByName(name);
        if (albums.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    // New endpoint to get albums added by the authenticated user
    @GetMapping("/user")
    public ResponseEntity<List<AlbumRequestDTO>> getAlbumsByUser() {
        List<AlbumRequestDTO> userAlbums = albumService.findByUser();
        if (userAlbums.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(userAlbums, HttpStatus.OK);
    }

    @PostMapping("/user/collection")
    public ResponseEntity<AlbumRequestDTO> saveAlbumToUser(@RequestBody AlbumRequestDTO albumRequestDTO) {
        // Validate mandatory fields
        System.out.println("Album Spotify ID in DTO: " + albumRequestDTO.getAlbumSpotifyID());

        if (albumRequestDTO.getCondition() == null ||
                albumRequestDTO.getFormat() == null ||
                albumRequestDTO.getTitle() == null ||
                albumRequestDTO.getArtist() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Fetch the authenticated user's details (you can still use this, depending on
        // the flow)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Alternatively, if you're using `utilizadorId`, fetch the user by that ID
        // directly
        Optional<User> user = userService.getUserById(albumRequestDTO.getUtilizadorId());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Album album = AlbumMapper.toEntity(albumRequestDTO);
        album.setUser(user.get()); // Assign the fetched user
        album.setAlbum_SpotifyID(albumRequestDTO.getAlbumSpotifyID());
        album.setCondition(albumRequestDTO.getCondition());
        album.setFormat(albumRequestDTO.getFormat());
        album.setName(albumRequestDTO.getTitle());
        album.setArtist(albumRequestDTO.getArtist());
        album.setGenre(albumRequestDTO.getGenre());
        // Save the album to the database
        Album savedAlbum = albumService.saveAlbum(album);
        // Map the saved album back to the DTO for the response
        AlbumRequestDTO savedAlbumDTO = AlbumMapper.toDTO(savedAlbum); // Use AlbumMapper here
        System.out.println("Album Spotify ID before saving: " + album.getAlbum_SpotifyID());

        return new ResponseEntity<>(savedAlbumDTO, HttpStatus.CREATED);
    }

}