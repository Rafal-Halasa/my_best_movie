package pl.simcodic.mybestmovie.domain.movie.local.data

import pl.simcodic.mybestmovie.data.movie.local.data.MovieEntity

data class MovieLocal(val id: Int, val isLike: Boolean)

fun MovieEntity.toMovieLocal() = with(this) { MovieLocal(id = id, isLike = isLike) }

fun MovieLocal.toMovieEntity() = with(this) { MovieEntity(id = id, isLike = isLike) }