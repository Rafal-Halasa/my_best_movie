package pl.simcodic.mybestmovie.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import pl.simcodic.mybestmovie.R


@Composable
fun MainScreen(viewModel: MainViewModel) {
    MainScreenContainer(viewModel.isError.collectAsState().value)
}

@Composable
fun MainScreenContainer(value: Boolean) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (value) {
            ErrorView()
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
fun ErrorView() {
    Box(modifier = Modifier
        .background(MaterialTheme.colorScheme.onBackground)
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp, 100.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_error),
                contentDescription = null
            )
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.ok))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        MainScreenContainer(false)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewError() {
    AppTheme {
        MainScreenContainer(true)
    }
}