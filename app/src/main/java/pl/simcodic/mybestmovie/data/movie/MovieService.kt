package pl.simcodic.mybestmovie.data.movie

import pl.simcodic.mybestmovie.data.movie.data.NowPlayingMoviesResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Call<NowPlayingMoviesResponseBody>

    @GET("search/movie")
    fun findMovies(@QueryMap text: Map<String, String>): Call<NowPlayingMoviesResponseBody>
}