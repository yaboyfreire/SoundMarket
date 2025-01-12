package com.example.loginauthapi.Mapper;

import com.example.loginauthapi.domain.Album;
import com.example.loginauthapi.dto.AlbumRequestDTO;

public class AlbumMapper {

    public static AlbumRequestDTO toDTO(Album album) {
        if (album == null) {
            return null;
        }

        AlbumRequestDTO dto = new AlbumRequestDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getName());
        dto.setArtist(album.getArtist());
        dto.setCondition(album.getCondition());
        dto.setFormat(album.getFormat());
        dto.setGenre(album.getGenre());
        dto.setAlbumSpotifyID(album.getAlbum_SpotifyID()); // Map Spotify ID
        dto.setImageURL(album.getImageURL()); // Map image URL
        dto.setAlbum_added_date(album.getAlbum_added_date()); // Map added date

        // Map User ID if available
        if (album.getUser() != null) {
            dto.setUtilizadorId(album.getUser().getId());
        }

        return dto;
    }

    public static Album toEntity(AlbumRequestDTO albumDTO) {
        Album album = new Album();
        album.setId(albumDTO.getId());
        album.setName(albumDTO.getTitle());
        album.setCondition(albumDTO.getCondition());
        album.setFormat(albumDTO.getFormat());
        album.setArtist(albumDTO.getArtist());
        album.setGenre(albumDTO.getGenre());
        album.setAlbum_SpotifyID(albumDTO.getAlbumSpotifyID()); // Map Spotify ID
        album.setImageURL(albumDTO.getImageURL()); // Map Image URL
        album.setAlbum_added_date(albumDTO.getAlbum_added_date()); // Map Added Date
        return album;
    }
}
