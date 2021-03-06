package web.id.wahyou.jetpackapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("title") var name: String? = null,
    @SerializedName("overview") var desc: String? = null,
    @SerializedName("poster_path") var poster: String? = null,
    @SerializedName("backdrop_path") var img_preview: String? = null,
    @SerializedName("release_date") val release_date: String?,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("popularity") val popularity: Double?
)