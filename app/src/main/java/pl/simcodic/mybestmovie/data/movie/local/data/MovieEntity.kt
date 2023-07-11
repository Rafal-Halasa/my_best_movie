package pl.simcodic.mybestmovie.data.movie.local.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.simcodic.mybestmovie.data.movie.local.data.ColumnInfo.IS_LIKE

@Entity
data class MovieEntity(@PrimaryKey val id: Int, @ColumnInfo(name = IS_LIKE) val isLike: Boolean)

object ColumnInfo {
    const val IS_LIKE = "isLike"
}