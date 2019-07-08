package gug.co.com.testmovies.data.domain

import gug.co.com.testmovies.data.source.remote.retrofit.IMAGE_PATH

data class ProductionCompany(

    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String

) {

    val definitiveLgoPath: String = getDefinitivePosterPath(logoPath)

    private fun getDefinitivePosterPath(logoPath: String?): String {

        if (logoPath != null) {
            return "$IMAGE_PATH$logoPath"
        }

        return ""

    }

}