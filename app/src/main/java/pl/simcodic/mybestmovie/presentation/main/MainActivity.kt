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
import pl.simcodic.mybestmovie.presentation.details.DetailsViewModel
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.ID
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.OVERVIEW
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.POSTER_PATH
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.RELEASE_DATE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.TITLE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_AVERAGE
import pl.simcodic.mybestmovie.presentation.main.ArgumentsNames.VOTE_COUNT
import pl.simcodic.mybestmovie.presentation.main.navigationdata.toArgString
import pl.simcodic.mybestmovie.presentation.main.viewdata.mapToNavigationData
import pl.simcodic.mybestmovie.presentation.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

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
                        }, mainViewModel)
                    }
                    composable(NavigationDirections.DETAILS.destination,
                        arguments = listOf(
                            navArgument(TITLE) { defaultValue = "" },
                            navArgument(POSTER_PATH) { defaultValue = "" },
                            navArgument(RELEASE_DATE) { defaultValue = "" },
                            navArgument(VOTE_AVERAGE) { defaultValue = "" },
                            navArgument(VOTE_COUNT) { defaultValue = "" }
                        )) {
                        DetailsScreen(onBack = {
                            navController.popBackStack(NavigationDirections.HOME.destination, false)
                        }, it.arguments, detailsViewModel)
                    }
                }
            }
        }
    }
}

enum class NavigationDirections(val destination: String) {
    HOME("home"),
    DETAILS("details_screen/?$ID={$ID}&$TITLE={$TITLE}&$POSTER_PATH={$POSTER_PATH}&$RELEASE_DATE={$RELEASE_DATE}&$VOTE_AVERAGE={$VOTE_AVERAGE}&$VOTE_COUNT={$VOTE_COUNT}&$OVERVIEW={$OVERVIEW}")
}

object ArgumentsNames {
    const val ID = "id"
    const val TITLE = "title"
    const val POSTER_PATH = "posterPath"
    const val RELEASE_DATE = "releaseDate"
    const val VOTE_AVERAGE = "voteAverage"
    const val OVERVIEW = "overview"
    const val VOTE_COUNT = "voteCount"
}