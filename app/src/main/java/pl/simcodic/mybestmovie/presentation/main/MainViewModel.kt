package pl.simcodic.mybestmovie.presentation.main

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
import pl.simcodic.mybestmovie.domain.movie.remote.FindMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.remote.FindMoviesUseCase.FindMoviesUseCaseInput
import pl.simcodic.mybestmovie.domain.movie.remote.GetNowPlayingMoviesUseCase
import pl.simcodic.mybestmovie.domain.movie.remote.GetNowPlayingMoviesUseCase.NowPlayingMoviesInput
import pl.simcodic.mybestmovie.presentation.main.viewdata.LocalMovieViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.MoviesViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToLocalMovieViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToMoviesViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.plus
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val findMoviesUseCase: FindMoviesUseCase,
    private val saveLocalMovieUseCase: SaveLocalMovieUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
) : ViewModel() {
    private var _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private var _movies = MutableStateFlow<MoviesViewData?>(null)
    val movies = _movies.asStateFlow()

    private var _findMovies = MutableStateFlow<MoviesViewData?>(null)
    val findMovies = _findMovies.asStateFlow()

    private var _localMovies = MutableStateFlow<List<LocalMovieViewData>>(emptyList())
    val localMovies = _localMovies.asStateFlow()

    init {
        onGetNowPlayingMovies(1)
        viewModelScope.launch {
            getLocalMoviesUseCase(NonInput).collect() { getMoviesLocalOutput ->
                _localMovies.value = getMoviesLocalOutput.localMovie.map {
                    it.mapToLocalMovieViewData()
                }
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

    fun onAddMovieToFavorite(id: Int, isLike: Boolean) {
        viewModelScope.launch {
            println("tutaj $isLike")
            saveLocalMovieUseCase(SaveLocalMovieUseCase.SaveLocalInput(LocalMovie(id, !isLike)))
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