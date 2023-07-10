package pl.simcodic.mybestmovie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.movie.FindMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.FindMoviesUseCase.FindMoviesUseCaseInput
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase
import pl.simcodic.mybestmovie.presentation.main.viewdata.NowPlayingMoviesViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToNowPlayingMoviesViewData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val findMoviesUseCase: FindMoviesUseCase
) : ViewModel() {
    private var _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private var _movies = MutableStateFlow<NowPlayingMoviesViewData?>(null)
    val movies = _movies.asStateFlow()

    private var _findMovies = MutableStateFlow<NowPlayingMoviesViewData?>(null)
    val findMovies = _findMovies.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                getNowPlayingMoviesUseCase(NonInput)
            }.onSuccess { value ->
                _movies.value = value.nowPlayingMovies.mapToNowPlayingMoviesViewData()
            }.onFailure {
                _isError.value = true
            }
        }
    }

    fun onDisableError() {
        _isError.value = false
    }

    fun onFindMovie(text: String) {
        viewModelScope.launch {
            runCatching {
                findMoviesUseCase(FindMoviesUseCaseInput(text))
            }.onSuccess { value ->
                _findMovies.value = value.nowPlayingMovies.mapToNowPlayingMoviesViewData()
            }
        }
    }

    fun onFindMovieClear() {
        _findMovies.value = null
    }
}