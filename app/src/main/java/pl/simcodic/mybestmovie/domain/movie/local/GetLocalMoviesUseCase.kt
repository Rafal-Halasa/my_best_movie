package pl.simcodic.mybestmovie.domain.movie.local

import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.base.Output
import pl.simcodic.mybestmovie.domain.base.UseCase
import pl.simcodic.mybestmovie.domain.movie.local.data.LocalMovie
import pl.simcodic.mybestmovie.domain.movie.local.data.toMovieLocal
import pl.simcodic.mybestmovie.domain.movie.local.repository.MovieLocalRepository
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(private val movieLocalRepository: MovieLocalRepository) :
    UseCase<NonInput, GetLocalMoviesUseCase.GetMoviesLocalOutput> {

    override fun run(input: NonInput): GetMoviesLocalOutput =
        GetMoviesLocalOutput(movieLocalRepository.getMovies().map { it.toMovieLocal() })


    data class GetMoviesLocalOutput(val localMovie: List<LocalMovie>) : Output
}