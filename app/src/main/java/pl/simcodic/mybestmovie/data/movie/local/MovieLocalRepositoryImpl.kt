package pl.simcodic.mybestmovie.data.movie.local

import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity
import pl.simcodic.mybestmovie.domain.movie.local.repository.MovieLocalRepository
import javax.inject.Inject

class MovieLocalRepositoryImpl @Inject constructor(private val movieDao: MovieDao) :
    MovieLocalRepository {

    override fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie = movie)
    }

    override fun getMovies(): List<MovieEntity> = movieDao.getAll()
}