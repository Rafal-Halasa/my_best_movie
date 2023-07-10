package pl.simcodic.mybestmovie.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pl.simcodic.mybestmovie.BuildConfig
import pl.simcodic.mybestmovie.data.movie.MovieRepositoryImpl
import pl.simcodic.mybestmovie.data.movie.MovieService
import pl.simcodic.mybestmovie.domain.movie.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_URL = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
class WebModules {

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun httpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val build = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer " + BuildConfig.TMDB_API_KEY)
            .build()
        return@addInterceptor chain.proceed(build)
    }.build()

    @Provides
    fun movieServiceProvide(retrofit: Retrofit) = retrofit.create(MovieService::class.java)
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}
