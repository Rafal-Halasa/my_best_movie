package pl.simcodic.mybestmovie.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.movie.local.GetLocalMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.local.SaveLocalMovieUseCase
import pl.simcodic.mybestmovie.domain.movie.local.data.LocalMovie
import pl.simcodic.mybestmovie.presentation.main.viewdata.LocalMovieViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToLocalMovieViewData
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val saveLocalMovieUseCase: SaveLocalMovieUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
) : ViewModel() {

    private var _localMovie = MutableStateFlow<List<LocalMovieViewData>>(emptyList())
    val localMovies = _localMovie.asStateFlow()

    init {
        viewModelScope.launch {
            getLocalMoviesUseCase(NonInput).collect() { getMoviesLocalOutput ->
                _localMovie.value = getMoviesLocalOutput.localMovie.map {
                    it.mapToLocalMovieViewData()
                }
            }
        }
    }

    fun onAddMovieToFavorite(id: Int, isLike: Boolean) {
        viewModelScope.launch {
            println("tutaj $isLike")
            saveLocalMovieUseCase(SaveLocalMovieUseCase.SaveLocalInput(LocalMovie(id, !isLike)))
        }
    }
}