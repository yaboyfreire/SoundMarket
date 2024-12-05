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
    val uri: String = ""
) {
    data class ExternalUrls(val spotify: String = "") // Nested class for external URLs

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
}
