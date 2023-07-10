package pl.simcodic.mybestmovie.presentation.main.viewdata

import pl.simcodic.mybestmovie.domain.movie.data.Movie

data class NowPlayingMovieViewData(val title: String, val posterPath: String)

fun Movie.toNowPlayingMovieViewData() = with(this) {
    NowPlayingMovieViewData(title = title, posterPath = posterPath)
}