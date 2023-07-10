package pl.simcodic.mybestmovie.domain.movie.data

import pl.simcodic.mybestmovie.data.movie.data.MovieResponseBody

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

fun MovieResponseBody.mapToMovie() = with(this) {
    Movie(
        adult = adult,
        backdropPath = backdrop_path,
        id = id,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        title = title,
        video = video,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}