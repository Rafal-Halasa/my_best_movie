package pl.simcodic.mybestmovie.domain.movie.remote.repository

import pl.simcodic.mybestmovie.data.movie.remote.data.NowPlayingMoviesResponseBody

interface MovieRepository {

    fun getNowPlayingMovies(page: Int): NowPlayingMoviesResponseBody

    fun findMovies(text: String): NowPlayingMoviesResponseBody
}