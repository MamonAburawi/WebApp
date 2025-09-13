package org.example.project.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.example.project.pages.Home
import org.example.project.pages.Product


@Composable
fun NavHost() {
    MaterialTheme{
        PreComposeApp {


            // 1. Use rememberNavigator() to get the controller
            val navigator = rememberNavigator()

            // 2. Use the NavHost composable
            NavHost(
                navigator = navigator,
                initialRoute = "/home", // Use string-based routes
            ) {
                // 3. Define the "scene" for each screen
                scene(route = "/home") {
                    Home(
                        onNavigateToProduct = { productId ->
                            // 4. Use navigator.push to navigate
                            navigator.navigate("/product/$productId")
                        }
                    )
                }

                scene(route = "/product/{id}") { backStackEntry ->
                    // Extract the argument from the back stack entry
                    val productId: String? = backStackEntry.path<String>("id")
                    if (productId != null) {
                        Product(
                            id = productId,
                            onBack = {
                                // 5. Use navigator.goBack to go back
                                navigator.goBack()
                            }
                        )
                    }
                }
            }

        }
    }
}


//    val backStack = rememberNavBackStack<Screen>(Screen.Home)

//    NavDisplay(
//        backStack = backStack,
//        onBack = { backStack.removeLastOrNull() },
//        entryProvider = entryProvider {
//
//            entry<Screen.Home>{
//                Home(
//                    onNavigateToProduct = { id ->
//                        backStack.add(Screen.Product(id))
//                    }
//                )
//            }
//
//
//            entry<Screen.Product>{ key ->
//                Product(id = key.id, onBack = {
//                    backStack.removeLastOrNull()
//                })
//            }
//
//
//        }
//    )



