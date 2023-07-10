package pl.simcodic.mybestmovie.domain.movie.repository

import pl.simcodic.mybestmovie.data.movie.data.NowPlayingMoviesResponseBody

interface MovieRepository {

    fun getNowPlayingMovies(): NowPlayingMoviesResponseBody
}