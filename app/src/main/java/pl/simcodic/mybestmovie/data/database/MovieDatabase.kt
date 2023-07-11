package pl.simcodic.mybestmovie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.simcodic.mybestmovie.data.movie.local.MovieDao
import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}