package com.example.loginauthapi.repositories;

import com.example.loginauthapi.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlbumRepository extends JpaRepository<Album, Integer> {

    // Método personalizado para buscar álbuns por artista
    List<Album> findByArtist(String artist);

    // Novo método para buscar álbuns por nome (insensível a maiúsculas/minúsculas)
    List<Album> findByNameContainingIgnoreCase(String name);

    List<Album> findByUser_UserName(String username);

    List<Album> findByUserId(Integer userId);

}
