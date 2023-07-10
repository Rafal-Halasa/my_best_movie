package pl.simcodic.mybestmovie.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
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
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun movieServiceProvide(retrofit: Retrofit) = retrofit.create(MovieService::class.java)
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}
