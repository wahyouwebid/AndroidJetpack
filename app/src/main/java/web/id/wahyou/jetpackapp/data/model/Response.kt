package web.id.wahyou.jetpackapp.data.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("results")
    val result: List<T>? = null
)