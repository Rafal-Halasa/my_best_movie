package pl.simcodic.mybestmovie.domain.movie.data

import pl.simcodic.mybestmovie.data.movie.data.NowPlayingMoviesResponseBody

data class NowPlayingMovies(val page: Int, val movies: List<Movie>)

fun NowPlayingMoviesResponseBody.mapToNowPlayingMovies() = with(this) {
    NowPlayingMovies(page = page, movies = result.map { movieBody -> movieBody.mapToMovie() })
}