package pl.simcodic.mybestmovie.domain.movie

import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.base.Output
import pl.simcodic.mybestmovie.domain.base.UseCase
import pl.simcodic.mybestmovie.domain.movie.data.NowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.data.mapToNowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<NonInput, NowPlayingMoviesOutput> {

    override fun run(input: NonInput): NowPlayingMoviesOutput =
        NowPlayingMoviesOutput(movieRepository.getNowPlayingMovies().mapToNowPlayingMovies())

}

data class NowPlayingMoviesOutput(val nowPlayingMovies: NowPlayingMovies) : Output