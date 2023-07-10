package pl.simcodic.mybestmovie.data.movie.data

data class MovieResponseBody(
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