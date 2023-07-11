package pl.simcodic.mybestmovie.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import pl.simcodic.mybestmovie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(onBack: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Row {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back),
                    contentDescription = null
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_border),
                    contentDescription = null
                )
            }
        }
    }) { content ->
        Box(modifier = Modifier.padding(content)) {
            Text(text = "hello")
        }
    }
}