package pl.simcodic.mybestmovie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.base.NonInput
import pl.simcodic.mybestmovie.domain.movie.GetNowPlayingMoviesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase) : ViewModel() {

    init {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase(NonInput)
        }
    }
}