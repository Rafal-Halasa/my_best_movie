package pl.simcodic.mybestmovie.presentation.main.viewdata

import pl.simcodic.mybestmovie.domain.movie.local.data.LocalMovie

data class LocalMovieViewData(val id: Int, val isLike: Boolean)

fun LocalMovie.mapToLocalMovieViewData() = with(this) {
    LocalMovieViewData(id = id, isLike = isLike)
}