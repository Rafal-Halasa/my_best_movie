package pl.simcodic.mybestmovie.presentation.main.viewdata

import pl.simcodic.mybestmovie.domain.movie.data.NowPlayingMovies

data class NowPlayingMoviesViewData(val page: Int, val movies: List<NowPlayingMovieViewData>)

fun NowPlayingMovies.mapToNowPlayingMoviesViewData() = with(this) {
    NowPlayingMoviesViewData(page = page, movies = movies.map { it.toNowPlayingMovieViewData() })
}