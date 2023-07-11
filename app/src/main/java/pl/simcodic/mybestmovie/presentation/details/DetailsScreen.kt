package pl.simcodic.mybestmovie.presentation.details

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.simcodic.mybestmovie.R
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.POSTER_PATH
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.RELEASE_DATA
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.TITLE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_AVERAGE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_COUNT

@Composable
fun DetailsScreen(onBack: () -> Unit, arguments: Bundle?) {
    arguments?.let {
        DetailsScreenContainer(
            onBack = onBack,
            title = it.getString(TITLE) ?: "",
            posterPath = it.getString(POSTER_PATH) ?: "",
            releaseData = it.getString(RELEASE_DATA) ?: "",
            voteAverage = it.getString(VOTE_AVERAGE) ?: "",
            voteCount = it.getString(VOTE_COUNT) ?: ""
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContainer(
    onBack: () -> Unit,
    title: String,
    posterPath: String,
    releaseData: String,
    voteAverage: String,
    voteCount: String
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
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_border),
                    contentDescription = null
                )
            }
        })
    }) { content ->
        Box(
            modifier = Modifier
                .padding(content)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500$posterPath",
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.5f))
            ) {
                Text(text = "testasd")
            }
        }
    }
}