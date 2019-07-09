package gug.co.com.testmovies.data.domain

const val PATH_THUMBNAILS = "http://img.youtube.com/vi/"
const val PATH_VIDEO = "http://www.youtube.com/watch?v="
const val IMA_0 = "/0.jpg"

data class MovieVideo(

    val key: String, // w-lUE5egBqs
    val name: String, // Marvel Studios' Avengers: Endgame | On Digital 7/30 & Blu-ray 8/13
    val site: String, // YouTube
    val type: String // Featurette

) {

    val thumbnailUrl = when (site) {
        "YouTube" -> "$PATH_THUMBNAILS$key$IMA_0"
        else -> ""
    }

    val videoUrl = when (site) {
        "YouTube" -> "$PATH_VIDEO$key"
        else -> ""
    }

}