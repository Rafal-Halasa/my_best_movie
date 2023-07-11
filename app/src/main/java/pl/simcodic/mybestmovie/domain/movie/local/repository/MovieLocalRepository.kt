package pl.simcodic.mybestmovie.domain.movie.local.repository

import kotlinx.coroutines.flow.Flow
import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity

interface MovieLocalRepository {

    suspend fun insertMovie(movie: MovieEntity)

    fun getMovies(): Flow<List<MovieEntity>>
}