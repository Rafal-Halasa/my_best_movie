package pl.simcodic.mybestmovie.data.movie.remote.data


data class NowPlayingMoviesResponseBody(
    val page: Int,
    val results: List<MovieResponseBody>,
    val total_pages: Int
)