package pl.simcodic.mybestmovie.presentation.details

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.simcodic.mybestmovie.R
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.ID
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.OVERVIEW
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.POSTER_PATH
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.RELEASE_DATE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.TITLE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_AVERAGE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_COUNT
import pl.simcodic.mybestmovie.presentation.main.viewdata.LocalMovieViewData
import pl.simcodic.mybestmovie.presentation.theme.AppTheme

@Composable
fun DetailsScreen(onBack: () -> Unit, arguments: Bundle?, detailsViewModel: DetailsViewModel) {
    arguments?.let {
        DetailsScreenContainer(
            detailsViewModel::onAddMovieToFavorite,
            onBack = onBack,
            id = it.getString(ID) ?: "",
            title = it.getString(TITLE) ?: "",
            posterPath = it.getString(POSTER_PATH) ?: "",
            releaseDate = it.getString(RELEASE_DATE) ?: "",
            voteAverage = it.getString(VOTE_AVERAGE) ?: "",
            voteCount = it.getString(VOTE_COUNT) ?: "",
            overview = it.getString(OVERVIEW) ?: "",
            detailsViewModel.localMovies.collectAsState().value,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContainer(
    onAddMovieToFavorite: (Int, Boolean) -> Unit,
    onBack: () -> Unit,
    id: String,
    title: String,
    posterPath: String,
    releaseDate: String,
    voteAverage: String,
    voteCount: String,
    overview: String,
    localMovies: List<LocalMovieViewData>
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = title)
        }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back),
                    contentDescription = null
                )

            }
        }, actions = {
            val findMovie = localMovies.find { it.id == id.toInt() }
            IconButton(onClick = {
                onAddMovieToFavorite(
                    id.toInt(),
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
        })
    }) { content ->
        Box(
            modifier = Modifier
                .padding(content)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$posterPath",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.9f))
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Release Date: $releaseDate",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Vote: $voteAverage Number of Vote: $voteCount",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Description: $overview",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        DetailsScreenContainer(
            { _, _ -> },
            {},
            "1",
            "Me and my dog",
            "/67ZsRKbItt6B1yHlsJKgfPWOyuJ.jpg",
            "2023-04-27",
            "6.5",
            "367",
            "When a headstrong street orphan, Seiya, in search of his abducted sister unwittingly taps into hidden powers, he discovers he might be the only person alive who can protect a reincarnated goddess, sent to watch over humanity. Can he let his past go and embrace his destiny to become a Knight of the Zodiac?",
            listOf()
        )
    }
}