package pl.simcodic.mybestmovie.domain.movie.repository

import pl.simcodic.mybestmovie.data.movie.data.NowPlayingMoviesResponseBody

interface MovieRepository {

    fun getNowPlayingMovies(page: Int): NowPlayingMoviesResponseBody

    fun findMovies(text: String): NowPlayingMoviesResponseBody
}