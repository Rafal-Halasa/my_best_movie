package pl.simcodic.mybestmovie.data.movie.local.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(@PrimaryKey val id: Int, @ColumnInfo(name = "last_name") val isLike: Boolean)
