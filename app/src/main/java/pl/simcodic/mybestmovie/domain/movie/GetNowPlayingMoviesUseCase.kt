package pl.simcodic.mybestmovie.domain.movie

import pl.simcodic.mybestmovie.domain.base.Input
import pl.simcodic.mybestmovie.domain.base.Output
import pl.simcodic.mybestmovie.domain.base.UseCase
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase.NowPlayingMoviesInput
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase.NowPlayingMoviesOutput
import pl.simcodic.mybestmovie.domain.movie.data.NowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.data.mapToNowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<NowPlayingMoviesInput, NowPlayingMoviesOutput> {

    override fun run(input: NowPlayingMoviesInput): NowPlayingMoviesOutput =
        NowPlayingMoviesOutput(
            movieRepository.getNowPlayingMovies(input.page).mapToNowPlayingMovies()
        )

    data class NowPlayingMoviesOutput(val nowPlayingMovies: NowPlayingMovies) : Output
    data class NowPlayingMoviesInput(val page: Int) : Input
}