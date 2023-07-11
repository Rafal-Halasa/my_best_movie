package pl.simcodic.mybestmovie.domain.movie.remote

import pl.simcodic.mybestmovie.domain.base.Input
import pl.simcodic.mybestmovie.domain.base.Output
import pl.simcodic.mybestmovie.domain.base.UseCase
import pl.simcodic.mybestmovie.domain.movie.remote.GetNowPlayingMoviesUseCase.NowPlayingMoviesInput
import pl.simcodic.mybestmovie.domain.movie.remote.GetNowPlayingMoviesUseCase.NowPlayingMoviesOutput
import pl.simcodic.mybestmovie.domain.movie.remote.data.NowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.remote.data.mapToNowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.remote.repository.MovieRepository
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