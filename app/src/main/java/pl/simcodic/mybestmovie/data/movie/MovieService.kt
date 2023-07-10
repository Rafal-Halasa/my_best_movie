package pl.simcodic.mybestmovie.data.movie

import pl.simcodic.mybestmovie.data.movie.data.NowPlayingMoviesResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Call<NowPlayingMoviesResponseBody>
}