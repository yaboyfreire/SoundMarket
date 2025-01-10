package pt.iade.ei.martim.rodrigo.SoundMarket.models

data class Album(
    val albumType: String = "",
    val artists: List<Artist> = emptyList(),
    val availableMarkets: List<String> = emptyList(),
    val externalUrls: ExternalUrls = ExternalUrls(),
    val href: String = "",
    val id: String = "",
    val images: List<Image> = emptyList(),
    val name: String = "",
    val release_date: String = "",
    val release_date_precision: String = "",
    val total_tracks: Int = 0,
    val type: String = "",
    val uri: String = "",
    val restrictions: Restrictions? = null,
    val tracks: Tracks = Tracks(),
    val copyrights: List<Copyright> = emptyList(),
    val externalIds: ExternalIds = ExternalIds(),
    val genres: List<String> = emptyList(),
    val label: String = "",
    val popularity: Int = 0
) {
    data class ExternalUrls(val spotify: String = "")

    data class Artist(
        val externalUrls: String = "",
        val href: String = "",
        val id: String = "",
        val name: String = "",
        val type: String = "",
        val uri: String = ""
    )

    data class Image(
        val height: Int = 0,
        val url: String = "",
        val width: Int = 0
    )

    data class Restrictions(
        val type: String = "",
        val uri: String = ""
    )

    data class Tracks(
        val items: List<Track> = emptyList()
    )

    data class Track(
        val name: String = "",
        val duration_ms: Int = 0,
        val preview_url: String? = null
    )

    data class Copyright(
        val text: String = "",
        val type: String = ""
    )

    data class ExternalIds(
        val isrc: String = ""
    )
}
