package pl.simcodic.mybestmovie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.movie.local.SaveLocalMovieUseCase
import pl.simcodic.mybestmovie.domain.movie.local.data.MovieLocal
import pl.simcodic.mybestmovie.domain.movie.remote.FindMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.remote.FindMoviesUseCase.FindMoviesUseCaseInput
import pl.simcodic.mybestmovie.domain.movie.remote.GetNowPlayingMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.remote.GetNowPlayingMoviesUseCase.NowPlayingMoviesInput
import pl.simcodic.mybestmovie.presentation.main.viewdata.MoviesViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToMoviesViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.plus
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val findMoviesUseCase: FindMoviesUseCase,
    private val saveLocalMovieUseCase: SaveLocalMovieUseCase,
) : ViewModel() {
    private var _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private var _movies = MutableStateFlow<MoviesViewData?>(null)
    val movies = _movies.asStateFlow()

    private var _findMovies = MutableStateFlow<MoviesViewData?>(null)
    val findMovies = _findMovies.asStateFlow()

    init {
        onGetNowPlayingMovies(1)
    }

    fun onDisableError() {
        _isError.value = false
    }

    fun onFindMovie(text: String) {
        viewModelScope.launch {
            runCatching {
                findMoviesUseCase(FindMoviesUseCaseInput(text))
            }.onSuccess { value ->
                _findMovies.value = value.nowPlayingMovies.mapToMoviesViewData()
            }
        }
    }

    fun onFindMovieClear() {
        _findMovies.value = null
    }

    fun onGetNowPlayingMoviesWithPagination(page: Int) {
        onGetNowPlayingMovies(page = page) { newPlayingMovies ->
            _movies.value?.let {
                _movies.value =
                    it + newPlayingMovies.nowPlayingMovies.mapToMoviesViewData()
            }
        }
    }

    fun onAddMovieToFavorite(id: Int) {
        viewModelScope.launch {
            saveLocalMovieUseCase(SaveLocalMovieUseCase.SaveLocalInput(MovieLocal(id, true)))
        }
    }

    private fun onGetNowPlayingMovies(
        page: Int,
        onFailureAction: (exception: Throwable) -> Unit = {
            _isError.value = true
        },
        onSuccessAction: (value: GetNowPlayingMoviesUseCase.NowPlayingMoviesOutput) -> Unit = {
            _movies.value = it.nowPlayingMovies.mapToMoviesViewData()
        },
    ) {
        viewModelScope.launch {
            runCatching {
                getNowPlayingMoviesUseCase(NowPlayingMoviesInput(page))
            }.onSuccess(onSuccessAction).onFailure(onFailureAction)
        }
    }
}