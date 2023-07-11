package pl.simcodic.mybestmovie.data.movie.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    fun getAll(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)
}