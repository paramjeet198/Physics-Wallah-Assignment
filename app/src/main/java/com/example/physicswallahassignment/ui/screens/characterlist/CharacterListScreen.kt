package com.example.physicswallahassignment.ui.screens.characterlist


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.physicswallahassignment.data.remote.model.Character
import com.example.physicswallahassignment.ui.components.ErrorState
import com.example.physicswallahassignment.ui.components.LoadingState
import com.example.physicswallahassignment.ui.navigation.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
//    val responseState by viewModel.characterList.collectAsStateWithLifecycle()
    val lazyPagingItems = viewModel.characterPager.collectAsLazyPagingItems()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Rick and Morty Characters") })
    }) { contentPadding ->

        CharacterList(
            lazyPagingItems = lazyPagingItems,
            onCharacterClick = { character ->
                navController.navigate(NavigationRoutes.DETAILS + "/${character.id}")
            },
            contentPadding = contentPadding,
        )
    }
}

@Composable
fun CharacterList(
    lazyPagingItems: LazyPagingItems<Character>,
    onCharacterClick: (Character) -> Unit,
    contentPadding: PaddingValues,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        verticalArrangement = Arrangement.Center
    ) {

        when (lazyPagingItems.loadState.refresh) {
            is LoadState.Error -> item { ErrorState(errorMessage = "Some Error Occurred") }
            LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Waiting for items to load from the backend",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                            LoadingState()
                        }
                    }
                }
            }

            is LoadState.NotLoading -> Unit
        }

        when (lazyPagingItems.loadState.append) {
            is LoadState.Error -> item { ErrorState(errorMessage = "Some Error Occurred") }
            LoadState.Loading -> {
                item { LoadingState() }
            }

            is LoadState.NotLoading -> Unit
        }


        items(count = lazyPagingItems.itemCount) { index ->
            val item = lazyPagingItems[index]!!
            CharacterItem(character = item, onClick = { onCharacterClick(item) })
        }

    }
}


@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = character.name.toString(), style = MaterialTheme.typography.titleLarge)
        }
    }
}


