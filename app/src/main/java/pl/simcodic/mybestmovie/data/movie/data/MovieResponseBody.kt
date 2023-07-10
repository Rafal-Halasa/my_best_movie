package pl.simcodic.mybestmovie.data.movie.data

data class MovieResponseBody(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)