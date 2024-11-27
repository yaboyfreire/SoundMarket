package pt.iade.ei.martim.rodrigo.SoundMarket.models

data class NewReleasesResponse(
    val albums: Albums
)

data class Albums(
    val items: List<Album>
)
