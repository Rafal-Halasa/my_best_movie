package pl.simcodic.mybestmovie.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pl.simcodic.mybestmovie.BuildConfig
import pl.simcodic.mybestmovie.data.database.MovieDatabase
import pl.simcodic.mybestmovie.data.movie.local.MovieLocalRepositoryImpl
import pl.simcodic.mybestmovie.data.movie.remote.MovieRepositoryImpl
import pl.simcodic.mybestmovie.data.movie.remote.MovieService
import pl.simcodic.mybestmovie.domain.movie.local.repository.MovieLocalRepository
import pl.simcodic.mybestmovie.domain.movie.remote.repository.MovieRepository
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
@InstallIn(SingletonComponent::class)
class Database {

    @Provides
    fun database(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        MovieDatabase::class.java, "movie_database"
    ).build()

    @Provides
    fun movieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieLocalRepository(movieRepositoryImpl: MovieLocalRepositoryImpl): MovieLocalRepository
}

