package pl.simcodic.mybestmovie.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.simcodic.mybestmovie.presentation.details.DetailsScreen
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
                        MainScreen(viewModel) {
                            navController.navigate(NavigationDirections.DETAILS.destination)
                        }
                    }
                    composable(NavigationDirections.DETAILS.destination) {
                        DetailsScreen {
                            navController.popBackStack(NavigationDirections.HOME.destination, false)
                        }
                    }
                }
            }
        }
    }
}

enum class NavigationDirections(val destination: String) {
    HOME("home"), DETAILS("details_screen")
}