package pl.simcodic.mybestmovie.data.movie.remote

import pl.simcodic.mybestmovie.data.movie.remote.data.NowPlayingMoviesResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@QueryMap text: Map<String, Int>): Call<NowPlayingMoviesResponseBody>

    @GET("search/movie")
    fun findMovies(@QueryMap text: Map<String, String>): Call<NowPlayingMoviesResponseBody>
}