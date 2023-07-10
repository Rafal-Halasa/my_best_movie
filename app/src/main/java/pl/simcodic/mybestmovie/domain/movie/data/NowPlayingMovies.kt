package pl.simcodic.mybestmovie.domain.movie.data

import pl.simcodic.mybestmovie.data.movie.data.NowPlayingMoviesResponseBody

data class NowPlayingMovies(val page: Int, val movies: List<Movie>, val totalPages: Int)

fun NowPlayingMoviesResponseBody.mapToNowPlayingMovies() = with(this) {
    NowPlayingMovies(
        page = page,
        movies = results.map { movieBody -> movieBody.mapToMovie() },
        totalPages = total_pages
    )
}