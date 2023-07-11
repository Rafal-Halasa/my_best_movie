package pl.simcodic.mybestmovie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.movie.FindMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.FindMoviesUseCase.FindMoviesUseCaseInput
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase.NowPlayingMoviesInput
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
                _findMovies.value = value.nowPlayingMovies.mapToNowPlayingMoviesViewData()
            }
        }
    }

    fun onFindMovieClear() {
        _findMovies.value = null
    }

    fun onGetNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            runCatching {
                getNowPlayingMoviesUseCase(NowPlayingMoviesInput(page))
            }.onSuccess { value ->
                _movies.value = value.nowPlayingMovies.mapToNowPlayingMoviesViewData()
            }.onFailure {
                println("tutaj $it")
                _isError.value = true
            }
        }
    }
}