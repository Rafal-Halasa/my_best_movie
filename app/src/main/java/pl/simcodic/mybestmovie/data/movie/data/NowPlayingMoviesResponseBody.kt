package pl.simcodic.mybestmovie.data.movie.data


data class NowPlayingMoviesResponseBody(
    val page: Int,
    val results: List<MovieResponseBody>,
    val total_pages: Int
)