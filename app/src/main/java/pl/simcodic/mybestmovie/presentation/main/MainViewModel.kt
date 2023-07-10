package pl.simcodic.mybestmovie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase
import pl.simcodic.mybestmovie.presentation.main.viewdata.NowPlayingMoviesViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToNowPlayingMoviesViewData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {
    private var _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private var _movies = MutableStateFlow<NowPlayingMoviesViewData?>(null)
    val movies = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                getNowPlayingMoviesUseCase(NonInput)
            }.onSuccess { value ->
                _movies.value = value.nowPlayingMovies.mapToNowPlayingMoviesViewData()
                println("tutaj " + _movies.value)
            }.onFailure {
                _isError.value = true
            }
        }
    }

    fun onDisableError() {
        _isError.value = false
    }

    fun onFindMovie(text: String) {

    }
}