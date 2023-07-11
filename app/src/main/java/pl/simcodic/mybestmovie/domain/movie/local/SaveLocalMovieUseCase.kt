package pl.simcodic.mybestmovie.domain.movie.local

import pl.simcodic.mybestmovie.domain.base.Input
import pl.simcodic.mybestmovie.domain.base.NonOutput
import pl.simcodic.mybestmovie.domain.base.UseCase
import pl.simcodic.mybestmovie.domain.movie.local.data.MovieLocal
import pl.simcodic.mybestmovie.domain.movie.local.data.toMovieEntity
import pl.simcodic.mybestmovie.domain.movie.local.repository.MovieLocalRepository
import javax.inject.Inject

class SaveLocalMovieUseCase @Inject constructor(private val movieLocalRepository: MovieLocalRepository) :
    UseCase<SaveLocalMovieUseCase.SaveLocalInput, NonOutput> {


    override fun run(input: SaveLocalInput): NonOutput {
        movieLocalRepository.insertMovie(input.movieLocal.toMovieEntity())
        return NonOutput
    }

    data class SaveLocalInput(val movieLocal: MovieLocal) : Input
}