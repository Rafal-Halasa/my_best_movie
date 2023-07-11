package pl.simcodic.mybestmovie.domain.movie.local.repository

import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity

interface MovieLocalRepository {

    fun insertMovie(movie: MovieEntity)

    fun getMovies() :List<MovieEntity>
}