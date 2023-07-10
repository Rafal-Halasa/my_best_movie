package pl.simcodic.mybestmovie.domain.movie

import pl.simcodic.mybestmovie.domain.base.Input
import pl.simcodic.mybestmovie.domain.base.Output
import pl.simcodic.mybestmovie.domain.base.UseCase
import pl.simcodic.mybestmovie.domain.movie.FindMoviesUseCase.FindMoviesUseCaseInput
import pl.simcodic.mybestmovie.domain.movie.FindMoviesUseCase.FindMoviesUseCaseOutput
import pl.simcodic.mybestmovie.domain.movie.data.NowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.data.mapToNowPlayingMovies
import pl.simcodic.mybestmovie.domain.movie.repository.MovieRepository
import javax.inject.Inject

class FindMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<FindMoviesUseCaseInput, FindMoviesUseCaseOutput> {

    override fun run(input: FindMoviesUseCaseInput): FindMoviesUseCaseOutput =
        FindMoviesUseCaseOutput(movieRepository.findMovies(input.text).mapToNowPlayingMovies())


    data class FindMoviesUseCaseOutput(val nowPlayingMovies: NowPlayingMovies) : Output

    data class FindMoviesUseCaseInput(val text: String) : Input
}
