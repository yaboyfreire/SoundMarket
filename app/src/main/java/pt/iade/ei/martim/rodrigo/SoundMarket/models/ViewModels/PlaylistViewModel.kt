    package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

    import androidx.lifecycle.ViewModel
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.State
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch
    import pt.iade.ei.martim.rodrigo.SoundMarket.repository.SpotifyRepository
    import pt.iade.ei.martim.rodrigo.SoundMarket.models.Playlist
    import pt.iade.ei.martim.rodrigo.SoundMarket.models.PlaylistResponse

    class PlaylistViewModel : ViewModel() {

        private val spotifyRepository = SpotifyRepository() // Assuming this is your Spotify API repository

        private val _playlist = MutableStateFlow<Playlist?>(null)
        val playlist: StateFlow<Playlist?> get() = _playlist

        // Fetch playlist

        private fun mapToPlaylist(response: PlaylistResponse): Playlist {
            return Playlist(
                name = response.name,
                tracks = Playlist.Tracks(
                    items = response.tracks.items.map { item ->
                        Playlist.Tracks.Item(
                            track = Playlist.Tracks.Track(
                                id = item.track.id,
                                name = item.track.name,
                                album = Playlist.Tracks.Album(
                                    id = item.track.album.id,
                                    name = item.track.album.name,
                                    artists = item.track.album.artists.map { artist ->
                                        Playlist.Tracks.Artist(name = artist.name)
                                    },
                                    images = item.track.album.images.map { image ->
                                        Playlist.Tracks.Image(url = image.url)
                                    }
                                )
                            )
                        )
                    }
                )
            )
        }

        // New function to get track and album IDs from the current playlist
        fun getTrackAndAlbumIds(): List<Pair<String, String>>? {
            return _playlist.value?.tracks?.items?.map {
                Pair(it.track.id, it.track.album.id) // Pair track ID with album ID
            }
        }

        fun getPlaylist(token: String, playlistId: String) {
            viewModelScope.launch {
                val playlistResponse = spotifyRepository.fetchPlaylist(token, playlistId)
                _playlist.value = mapToPlaylist(playlistResponse)
            }
        }
    }
