package com.example.navigationthreeapp

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

sealed interface HomeKey {
    data object Screen1 : HomeKey
    data class Screen2(val id: String) : HomeKey
}

@Composable
fun HomeDisplay() {
    val backStack = remember { mutableStateListOf<HomeKey>(HomeKey.Screen1) }
    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeLast() },
        predictivePopTransitionSpec = {
            ContentTransform(
                fadeIn(tween(300)),
                fadeOut(tween(300))
            )
        },
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
                Text("Screen 2, ID: $id")
            }
        }
    )
}