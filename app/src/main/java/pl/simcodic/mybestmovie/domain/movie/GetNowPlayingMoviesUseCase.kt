package pl.simcodic.mybestmovie.domain.movie

import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.base.Output
import pl.simcodic.mybestmovie.domain.base.UseCase
import pl.simcodic.mybestmovie.domain.movie.data.NowPlayingMovies
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor() : UseCase<NonInput, NowPlayingMoviesOutput> {

    override fun run(input: NonInput): NowPlayingMoviesOutput {
        return NowPlayingMoviesOutput(NowPlayingMovies(1, listOf()))
    }
}

data class NowPlayingMoviesOutput(val nowPlayingMovies: NowPlayingMovies) : Output