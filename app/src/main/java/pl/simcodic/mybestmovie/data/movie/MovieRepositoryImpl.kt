package pl.simcodic.mybestmovie.data.movie

import pl.simcodic.mybestmovie.data.movie.data.NowPlayingMoviesResponseBody
import pl.simcodic.mybestmovie.domain.movie.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) : MovieRepository {

    override fun getNowPlayingMovies(): NowPlayingMoviesResponseBody =
        with(service.getNowPlayingMovies().execute()) {
            if (this.isSuccessful) {
                this.body() ?: throw Exception("Body is empty")
            } else {
                throw Exception("Not success response")
            }
        }

    override fun findMovies(text: String): NowPlayingMoviesResponseBody =
        with(service.findMovies(mapOf("query" to text)).execute()) {
            if (this.isSuccessful) {
                println(this.body())
                println(text)
                this.body() ?: throw Exception("Body is empty")
            } else {
                throw Exception("Not success response")
            }
        }
}