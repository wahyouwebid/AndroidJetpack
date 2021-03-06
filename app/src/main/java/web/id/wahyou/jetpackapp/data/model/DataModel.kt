package web.id.wahyou.jetpackapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataModel(
    var id: Int = 0,
    var name: String? = null,
    var desc: String? = null,
    var poster: String? = null,
    var img_preview: String? = null,
    var release_date: String? = null,
    var vote_average: Double? = null,
    var popularity: Double? = null
) : Parcelable