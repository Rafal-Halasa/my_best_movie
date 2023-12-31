package pl.simcodic.mybestmovie.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.simcodic.mybestmovie.R
import pl.simcodic.mybestmovie.presentation.main.viewdata.LocalMovieViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.MovieViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.MoviesViewData
import pl.simcodic.mybestmovie.presentation.theme.AppTheme


@Composable
fun MainScreen(onDetailsGo: (MovieViewData) -> Unit, viewModel: MainViewModel) {
    MainScreenContainer(
        viewModel.movies.collectAsState().value,
        viewModel.findMovies.collectAsState().value,
        viewModel.localMovies.collectAsState().value,
        viewModel.isError.collectAsState().value,
        viewModel::onDisableError,
        viewModel::onFindMovie,
        viewModel::onFindMovieClear,
        viewModel::onGetNowPlayingMoviesWithPagination,
        viewModel::onAddMovieToFavorite,
        onDetailsGo,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContainer(
    movies: MoviesViewData?,
    findMovies: MoviesViewData?,
    localMovie: List<LocalMovieViewData>,
    isError: Boolean,
    disableError: () -> Unit,
    onFindMovie: (String) -> Unit,
    onFindMovieClear: () -> Unit,
    onGetNowPlayingMoviesWithPagination: (Int) -> Unit,
    onAddMovieToFavorite: (Int, Boolean) -> Unit,
    onDetailsGo: (MovieViewData) -> Unit,
) {
    var textField by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        SearchView(textField, onFindMovieClear) {
            if (it.length >= 3) {
                onFindMovie(it)
            } else {
                onFindMovieClear()
            }
            textField = it
        }
    }) { content ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(content),
            contentAlignment = Alignment.Center
        ) {
            if (movies != null) {
                Box {
                    MoviesList(
                        onGetNowPlayingMovies = onGetNowPlayingMoviesWithPagination,
                        onAddMovieToFavorite = onAddMovieToFavorite,
                        moviesData = movies,
                        onDetailsGo = onDetailsGo,
                        localMovie = localMovie
                    )
                    findMovies?.movies?.let {
                        AutoFillView(onDetailsGo = onDetailsGo, movies = it)
                    }
                }
            } else {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }

            if (isError) {
                ErrorView(disableError)
            }
        }
    }
}

@Composable
fun AutoFillView(onDetailsGo: (MovieViewData) -> Unit, movies: List<MovieViewData>) {
    if (movies.isNotEmpty()) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
            ) {
                items(movies) {
                    Divider(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        thickness = 1.dp
                    )

                    Text(
                        text = it.title,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .clickable { onDetailsGo(it) })
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(value: String, onFindMovieClear: () -> Unit, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(R.string.find_movie)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search),
                contentDescription = null
            )
        },
        trailingIcon = {
            if (value.length >= 3) {
                IconButton(onClick = onFindMovieClear) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun MoviesList(
    onGetNowPlayingMovies: (Int) -> Unit,
    onDetailsGo: (MovieViewData) -> Unit,
    onAddMovieToFavorite: (Int, Boolean) -> Unit,
    moviesData: MoviesViewData,
    modifier: Modifier = Modifier,
    localMovie: List<LocalMovieViewData>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp), modifier = modifier
    ) {
        items(moviesData.movies) { movie ->
            Card(
                modifier = Modifier
                    .height(250.dp)
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Box(modifier = Modifier.clickable(onClick = { onDetailsGo(movie) })) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        val findMovie = localMovie.find { it.id == movie.id }
                        IconButton(onClick = {
                            onAddMovieToFavorite(
                                movie.id,
                                findMovie?.isLike ?: false
                            )
                        }) {
                            if (findMovie != null && findMovie.isLike) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_star),
                                    contentDescription = null
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_star_border),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            if (moviesData.page != moviesData.totalPages) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { onGetNowPlayingMovies(moviesData.page + 1) },
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        Text(text = stringResource(R.string.more))
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorView(disableError: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp, 100.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_error),
                contentDescription = null
            )
            Text(text = stringResource(R.string.something_go_wrong))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(onClick = disableError, modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(text = stringResource(R.string.ok))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        MainScreenContainer(
            MoviesViewData(
                1,
                listOf(), 2
            ),
            MoviesViewData(
                1,
                listOf(), 2
            ), listOf(), false, {}, {}, {}, {}, { _, _ -> }, {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewError() {
    AppTheme {
        MainScreenContainer(
            MoviesViewData(
                1,
                listOf(), 2
            ),
            MoviesViewData(
                1,
                listOf(), 2
            ), listOf(), true, {}, {}, {}, {}, { _, _ -> }, {}
        )
    }
}