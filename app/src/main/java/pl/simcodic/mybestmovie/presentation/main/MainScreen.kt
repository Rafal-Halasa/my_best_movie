package pl.simcodic.mybestmovie.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.compose.AppTheme
import pl.simcodic.mybestmovie.R


@Composable
fun MainScreen(viewModel: MainViewModel) {
    MainScreenContainer(viewModel.isError.collectAsState().value, viewModel::disableError)
}

@Composable
fun MainScreenContainer(value: Boolean, disableError: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (value) {
            ErrorView(disableError)
        }
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
        MainScreenContainer(false, {})
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewError() {
    AppTheme {
        MainScreenContainer(true, {})
    }
}