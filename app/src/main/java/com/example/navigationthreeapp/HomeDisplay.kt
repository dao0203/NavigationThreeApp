package com.example.navigationthreeapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable

sealed interface HomeKey : NavKey {
    @Serializable
    data object Screen1 : HomeKey

    @Serializable
    data class Screen2(val id: String) : HomeKey
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeDisplay() {
    val backStack = rememberNavBackStack(HomeKey.Screen1)
    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<HomeKey.Screen1> {
                LazyColumn {
                    items(10) {
                        Button(
                            onClick = { backStack.add(HomeKey.Screen2(id = it.toString())) }
                        ) {
                            Text("Go to Screen 2: $it")
                        }
                    }
                }
            }
            entry<HomeKey.Screen2> {
                val id = it.id
                LazyColumn {
                    items(50) {
                        Text("Item $it of Screen 2 (id=$id)")
                    }
                }
            }
        }
    )
}