package pl.simcodic.mybestmovie.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.simcodic.mybestmovie.R
import pl.simcodic.mybestmovie.presentation.main.viewdata.NowPlayingMovieViewData
import pl.simcodic.mybestmovie.presentation.main.viewdata.NowPlayingMoviesViewData
import pl.simcodic.mybestmovie.presentation.theme.AppTheme


@Composable
fun MainScreen(viewModel: MainViewModel) {
    MainScreenContainer(
        viewModel.movies.collectAsState().value,
        viewModel.isError.collectAsState().value,
        viewModel::disableError
    )
}

@Composable
fun MainScreenContainer(
    movies: NowPlayingMoviesViewData?,
    isError: Boolean,
    disableError: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (isError) {
                ErrorView(disableError)
            }
            if (movies != null) {
                MoviesList(movies.movies)
            } else {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }
        }
    }
}

@Composable
fun MoviesList(moviesData: List<NowPlayingMovieViewData>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(moviesData.size) {
            val movie = moviesData[it]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Row {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                        contentDescription = null,
                        modifier = Modifier.height(200.dp)
                    )
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(text = "${movie.voteAverage} / ${movie.voteCount}")
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = movie.releaseDate, style = MaterialTheme.typography.bodyMedium)
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
            Text(text = "Sorry but something go wrong")
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
        MainScreenContainer(NowPlayingMoviesViewData(1, listOf()), false) {}
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewError() {
    AppTheme {
        MainScreenContainer(NowPlayingMoviesViewData(1, listOf()), true) {}
    }
}