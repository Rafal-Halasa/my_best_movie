package pl.simcodic.mybestmovie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase) :
    ViewModel() {
    private var _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching { getNowPlayingMoviesUseCase(NonInput) }.onFailure {
                _isError.value = true
            }
        }
    }

    fun disableError() {
        _isError.value = false
    }
}