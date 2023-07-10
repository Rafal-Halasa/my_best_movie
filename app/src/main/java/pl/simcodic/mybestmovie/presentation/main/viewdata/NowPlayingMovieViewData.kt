package pl.simcodic.mybestmovie.presentation.main.viewdata

import pl.simcodic.mybestmovie.domain.movie.data.Movie

data class NowPlayingMovieViewData(
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)

fun Movie.toNowPlayingMovieViewData() = with(this) {
    NowPlayingMovieViewData(
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}