package pl.simcodic.mybestmovie.presentation.main.viewdata

import pl.simcodic.mybestmovie.domain.movie.remote.data.Movie
import pl.simcodic.mybestmovie.presentation.main.navigationdata.DetailsNavigationData

data class MovieViewData(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String,
    val voteCount: Int
)

fun Movie.toMovieViewData() = with(this) {
    MovieViewData(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        overview = overview,
        voteCount = voteCount
    )
}

fun MovieViewData.mapToNavigationData() = with(this) {
    DetailsNavigationData(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        overview = overview,
        voteCount = voteCount
    )
}
