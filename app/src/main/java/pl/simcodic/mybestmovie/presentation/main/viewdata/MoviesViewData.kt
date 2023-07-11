package pl.simcodic.mybestmovie.presentation.main.viewdata

import pl.simcodic.mybestmovie.domain.movie.remote.data.NowPlayingMovies

data class MoviesViewData(
    val page: Int,
    val movies: List<MovieViewData>,
    val totalPages: Int
)

fun NowPlayingMovies.mapToMoviesViewData() = with(this) {
    MoviesViewData(
        page = page,
        movies = movies.map { it.toMovieViewData() },
        totalPages = totalPages
    )
}

operator fun MoviesViewData.plus(newMoviesViewData: MoviesViewData) =
    MoviesViewData(
        page = newMoviesViewData.page,
        movies = this.movies + newMoviesViewData.movies,
        totalPages = newMoviesViewData.totalPages
    )
