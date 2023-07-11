package pl.simcodic.mybestmovie.domain.movie.local.data

import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity

data class LocalMovie(val id: Int, val isLike: Boolean)

fun MovieEntity.toMovieLocal() = with(this) { LocalMovie(id = id, isLike = isLike) }

fun LocalMovie.toMovieEntity() = with(this) { MovieEntity(id = id, isLike = isLike) }