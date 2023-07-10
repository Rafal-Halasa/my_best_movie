package pl.simcodic.mybestmovie.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.simcodic.mybestmovie.domain.movie.GetMoviesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    init {
        viewModelScope.launch {
            getMoviesUseCase()
        }
    }
}