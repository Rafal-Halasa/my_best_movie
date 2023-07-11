package pl.simcodic.mybestmovie.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pl.simcodic.mybestmovie.presentation.details.DetailsScreen
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.POSTER_PATH
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.RELEASE_DATA
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.TITLE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_AVERAGE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_COUNT
import pl.simcodic.mybestmovie.presentation.main.navigationdata.toArgString
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToNavigationData
import pl.simcodic.mybestmovie.presentation.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavigationDirections.HOME.destination
                ) {
                    composable(NavigationDirections.HOME.destination) {
                        MainScreen(onDetailsGo = {
                            navController.navigate(
                                "details_screen/?${
                                    it.mapToNavigationData().toArgString()
                                }"
                            )
                        }, viewModel)
                    }
                    composable(NavigationDirections.DETAILS.destination,
                        arguments = listOf(
                            navArgument(TITLE) { defaultValue = "" },
                            navArgument(POSTER_PATH) { defaultValue = "" },
                            navArgument(RELEASE_DATA) { defaultValue = "" },
                            navArgument(VOTE_AVERAGE) { defaultValue = "" },
                            navArgument(VOTE_COUNT) { defaultValue = "" }
                        )) {
                        DetailsScreen(onBack = {
                            navController.popBackStack(NavigationDirections.HOME.destination, false)
                        }, it.arguments)
                    }
                }
            }
        }
    }
}

enum class NavigationDirections(val destination: String) {
    HOME("home"),
    DETAILS("details_screen/?$TITLE={$TITLE}&$POSTER_PATH={$POSTER_PATH}&$RELEASE_DATA={$RELEASE_DATA}&$VOTE_AVERAGE={$VOTE_AVERAGE}&$VOTE_COUNT={$VOTE_COUNT}")
}

object ArgumentsNames {
    const val TITLE = "title"
    const val POSTER_PATH = "posterPath"
    const val RELEASE_DATA = "releaseDate"
    const val VOTE_AVERAGE = "voteAverage"
    const val VOTE_COUNT = "voteCount"
}