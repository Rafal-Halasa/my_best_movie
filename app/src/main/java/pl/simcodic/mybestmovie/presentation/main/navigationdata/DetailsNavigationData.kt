package pl.simcodic.mybestmovie.presentation.main.navigationdata

import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.ID
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.OVERVIEW
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.POSTER_PATH
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.RELEASE_DATE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.TITLE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_AVERAGE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_COUNT


data class DetailsNavigationData(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String,
    val voteCount: Int
)

fun DetailsNavigationData.toArgString() =
    "$ID=$id&$TITLE=$title&$POSTER_PATH=$posterPath&$RELEASE_DATE=$releaseDate&$VOTE_AVERAGE=$voteAverage&$VOTE_COUNT=$voteCount&$OVERVIEW=$overview"