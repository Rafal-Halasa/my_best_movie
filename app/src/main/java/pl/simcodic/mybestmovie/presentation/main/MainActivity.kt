package pl.simcodic.mybestmovie.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.simcodic.mybestmovie.presentation.main.Destinations.*
import pl.simcodic.mybestmovie.presentation.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = HOME.value) {
                    composable(HOME.value) {
                        MainScreen(viewModel)
                    }
                    composable(DETAILS.value){
                        MainScreen(viewModel)
                    }
                }
            }
        }
    }
}

enum class Destinations(val value: String) {
    HOME("home"), DETAILS("details_screen")
}