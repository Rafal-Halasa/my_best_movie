package pl.simcodic.mybestmovie.data.movie.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)
}