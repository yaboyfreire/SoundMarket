package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.AlbumViewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.*

class AlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Pass mock data for preview or actual parameters for dynamic content
            val albumId = intent.getStringExtra("ALBUM_ID") ?: ""
            val authToken = intent.getStringExtra("AUTH_TOKEN") ?: ""
            AlbumScreen(albumId = albumId, authToken = authToken)
        }
    }
}

@Composable
fun AlbumScreen(albumId: String, authToken: String) {
    val albumViewModel: AlbumViewModel = viewModel()
    val album by albumViewModel.album.observeAsState()

    // Fetch album data when screen is first launched
    LaunchedEffect(albumId) {
        val token = "Bearer BQCg8UIAN8uxP4EqBVhuB3VxzumJE8InsEqN6Gp_YgcE7M5dhSL-eE71n3HBJUczGUkvfsEnx-hpLTEEPbDTzG3-ywb3BbgWbPRJesoyAipEdj-sQIQ" // Replace with your actual token
        albumViewModel.fetchAlbum(albumId, token)
    }

    // Show loading indicator while waiting for album data
    if (album == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Align the loading indicator to the center
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Display album data when available
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            album?.let { albumData ->
                // Pass the correct data to the composables
                AlbumTopBar()  // Add any parameters if needed here
                Spacer(modifier = Modifier.height(16.dp))
                AlbumDetailsSection(album = albumData)  // Pass 'albumData' to AlbumDetailsSection
                Spacer(modifier = Modifier.height(16.dp))
                AlbumActions(album = albumData)  // Pass 'albumData' to AlbumActions
                Spacer(modifier = Modifier.height(16.dp))
                TrackListSection(tracks = albumData.tracks.items)  // Pass the tracks list from 'albumData'
                Spacer(modifier = Modifier.height(16.dp))
                AlbumMetadata(album = albumData)  // Pass 'albumData' to AlbumMetadata
                Spacer(modifier = Modifier.height(16.dp))
                AlbumCopiesButton()  // Add parameters if needed
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumScreenPreview() {
    // Mocked data for preview
    val mockAlbum = Album(
        id = "12345",
        name = "Mock Album",
        release_date = "2023-12-25",
        artists = listOf(Album.Artist(name = "Mock Artist")),
        images = listOf(Album.Image(url = "https://example.com/album_cover.jpg", height = 250, width = 250)),
        tracks = Album.Tracks(items = listOf(Album.Track(name = "Track 1", duration_ms = 200000))),
        externalUrls = Album.ExternalUrls(spotify = "https://spotify.com")
    )

    AlbumScreen(albumId = mockAlbum.id, authToken = "mockAuthToken")
}
