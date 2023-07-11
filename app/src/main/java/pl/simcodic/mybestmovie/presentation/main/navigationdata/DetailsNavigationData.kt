package pl.simcodic.mybestmovie.presentation.main.navigationdata

import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.POSTER_PATH
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.RELEASE_DATA
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.TITLE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_AVERAGE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_COUNT


data class DetailsNavigationData(
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)

fun DetailsNavigationData.toArgString() =
    "$TITLE={$title}&$POSTER_PATH={$posterPath}&${RELEASE_DATA}={${releaseDate}}&${VOTE_AVERAGE}={${voteAverage}}&${VOTE_COUNT}={${voteCount}}"