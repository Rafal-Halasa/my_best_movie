package pl.simcodic.mybestmovie.domain.movie.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.base.Output
import pl.simcodic.mybestmovie.domain.base.UseCaseAsFlow
import pl.simcodic.mybestmovie.domain.movie.local.data.LocalMovie
import pl.simcodic.mybestmovie.domain.movie.local.data.toMovieLocal
import pl.simcodic.mybestmovie.domain.movie.local.repository.MovieLocalRepository
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(private val movieLocalRepository: MovieLocalRepository) :
    UseCaseAsFlow<NonInput, Flow<GetLocalMoviesUseCase.GetMoviesLocalOutput>> {

    override fun run(input: NonInput): Flow<GetMoviesLocalOutput> =
        movieLocalRepository.getMovies()
            .map { movieEntities ->
                GetMoviesLocalOutput(movieEntities.map { it.toMovieLocal() }) }

    data class GetMoviesLocalOutput(val localMovie: List<LocalMovie>) : Output
}