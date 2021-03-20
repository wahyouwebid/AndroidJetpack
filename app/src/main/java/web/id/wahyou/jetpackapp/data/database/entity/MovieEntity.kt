package web.id.wahyou.jetpackapp.data.database.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "desc")
    var desc: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "img_preview")
    var imgPreview: String? = null,

    @NonNull
    @ColumnInfo(name = "release_date")
    val release_date: String?,

    @NonNull
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,

    @NonNull
    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,

): Parcelable