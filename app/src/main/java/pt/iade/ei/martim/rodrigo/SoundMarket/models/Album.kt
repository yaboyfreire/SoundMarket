package pt.iade.ei.martim.rodrigo.SoundMarket.models

data class Album(
    val albumType: String,
    val artists: List<Artist>,
    val availableMarkets: List<String>,
    val externalUrls: ExternalUrls, // This uses the nested ExternalUrls class
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val totalTracks: Int,
    val type: String,
    val uri: String
) {
    data class ExternalUrls(val spotify: String) // Nested class for external URLs
    data class Artist(
        val externalUrls: String,
        val href: String,
        val id: String,
        val name: String,
        val type: String,
        val uri: String
    )
    data class Image(
        val height: Int,
        val url: String,
        val width: Int
    )
}
